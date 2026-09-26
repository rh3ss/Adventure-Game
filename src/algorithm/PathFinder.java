package algorithm;

import entity.Entity;
import main.GamePanel;
import npc.NPC;
import tileFix.TileFix;
import tileInteractive.InteractiveTile;

import java.awt.*;
import java.util.ArrayList;

public class PathFinder {
    private final GamePanel gamePanel;
    private Node[][] nodes;
    private final ArrayList<Node> openList;
    public ArrayList<Node> pathList;
    private Node startNode, currentNode, destinationNode;
    private boolean destinationReached;
    private int step;
    private final int MAX_STEP_ALLOWED = 500;

    public PathFinder(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.openList = new ArrayList<>();
        this.pathList = new ArrayList<>();
        this.destinationReached = false;
        this.step = 0;

        this.initNodes();
    }

    private void initNodes() {
        this.nodes = new Node[this.gamePanel.maxWorldColumns][this.gamePanel.maxWorldRows];
        for (int col = 0; col < this.gamePanel.maxWorldColumns; ++col) {
            for (int row = 0; row < this.gamePanel.maxWorldRows; ++row) {
                this.nodes[col][row] = new Node(col, row);
            }
        }
    }

    private void resetNode() {
        for (int col = 0; col < this.gamePanel.maxWorldColumns; ++col) {
            for (int row = 0; row < this.gamePanel.maxWorldRows; ++row) {
                this.nodes[col][row].isOpen = false;
                this.nodes[col][row].isChecked = false;
                this.nodes[col][row].isSolid = false;
            }
        }
        this.openList.clear();
        this.pathList.clear();
        this.destinationReached = false;
        this.step = 0;
    }

    public void setNodes(int startColumn, int startRow, int destinationColumn, int destinationRow) {
        this.resetNode();
        this.startNode = this.nodes[startColumn][startRow];
        this.currentNode = this.startNode;
        this.destinationNode = this.nodes[destinationColumn][destinationRow];
        this.openList.add(this.currentNode);

        for (int col = 0; col < this.gamePanel.maxWorldColumns; ++col) {
            for (int row = 0; row < this.gamePanel.maxWorldRows; ++row) {
                // check normal tiles
                int tileNumber = this.gamePanel.tileManager.mapTileNumbers[this.gamePanel.currentMapNumber][col][row];
                if (this.gamePanel.tileManager.tiles.get(tileNumber).isSolid) {
                    this.nodes[col][row].isSolid = true;
                }
                // check interactive tiles
                for (Entity entity : this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities) {
                    if (entity instanceof InteractiveTile interactiveTile && interactiveTile.isDestructible) {
                        int interactiveColumn = interactiveTile.worldX / this.gamePanel.tileSize;
                        int interactiveRow = interactiveTile.worldY / this.gamePanel.tileSize;
                        this.nodes[interactiveColumn][interactiveRow].isSolid = true;
                    }
                }
                // check fix tiles
                for (Entity entity : this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities) {
                    if (entity instanceof TileFix tileFix && tileFix.isSolid) {
                        // tile fix might bigger so set all affected tiles solid
                        Rectangle solidArea = tileFix.solidArea;
                        int leftCol = (tileFix.worldX + solidArea.x) / this.gamePanel.tileSize;
                        int rightCol = (tileFix.worldX + solidArea.x + solidArea.width) / this.gamePanel.tileSize;
                        int topRow = (tileFix.worldY + solidArea.y) / this.gamePanel.tileSize;
                        int bottomRow = (tileFix.worldY + solidArea.y + solidArea.height) / this.gamePanel.tileSize;
                        for (int idxColumn = leftCol; idxColumn <= rightCol; ++idxColumn) {
                            for (int idxRow = topRow; idxRow <= bottomRow; ++idxRow) {
                                this.nodes[idxColumn][idxRow].isSolid = true;
                            }
                        }
                    }
                }
                // check fix placed NPC
                for (Entity entity : this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities) {
                    if (entity instanceof NPC npc && npc.isFixPlaced) {
                        int interactiveColumn = npc.worldX / this.gamePanel.tileSize;
                        int interactiveRow = npc.worldY / this.gamePanel.tileSize;
                        this.nodes[interactiveColumn][interactiveRow].isSolid = true;
                    }
                }
                // set players current position also solid
                int playerColumn = this.gamePanel.player.worldX / this.gamePanel.tileSize;
                int playerRow= this.gamePanel.player.worldY / this.gamePanel.tileSize;
                this.nodes[playerColumn][playerRow].isSolid = true;

                // set costs
                this.getNodeCost(this.nodes[col][row]);
            }
        }
    }

    private void getNodeCost(Node node) {
        // g Cost
        int xDistance = Math.abs(node.column - startNode.column);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        // h Cost
        xDistance = Math.abs(node.column - destinationNode.column);
        yDistance = Math.abs(node.row - destinationNode.row);
        node.hCost = xDistance + yDistance;
        // f Cost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean searchDestination() {
        while (!this.destinationReached && step < this.MAX_STEP_ALLOWED) {
            int column = this.currentNode.column;
            int row = this.currentNode.row;
            // check current Node
            this.currentNode.isChecked = true;
            this.openList.remove(this.currentNode);
            // open north, east, south, west nodes
            if (row - 1 >= 0) { this.openNode(this.nodes[column][row - 1]); }
            if (column + 1 < this.gamePanel.maxWorldColumns) { this.openNode(this.nodes[column + 1][row]); }
            if (row + 1 < this.gamePanel.maxWorldRows) { this.openNode(this.nodes[column][row + 1]); }
            if (column - 1 >= 0) { this.openNode(this.nodes[column - 1][row]); }

            // find best node
            int bestNodeIdx = Integer.MIN_VALUE;
            int bestNodeFCost = Integer.MAX_VALUE;
            for (int idx = 0; idx < this.openList.size(); ++idx) {
                // check if F Cost is better
                if (this.openList.get(idx).fCost < bestNodeFCost) {
                    bestNodeIdx = idx;
                    bestNodeFCost = this.openList.get(idx).fCost;
                }
                // if F Cost is equal, check G Cost
                else if (this.openList.get(idx).fCost == bestNodeFCost) {
                    if (this.openList.get(idx).gCost < this.openList.get(bestNodeIdx).gCost) {
                        bestNodeIdx = idx;
                    }
                }
            }
            // if openList is empty, break loop
            if (this.openList.isEmpty()) {
                break;
            }
            this.currentNode = this.openList.get(bestNodeIdx);
            if (this.currentNode == this.destinationNode) {
                this.destinationReached = true;
                this.trackDestinationPath();
            }
            this.step++;
        }
        return this.destinationReached;
    }

    private void openNode(Node node) {
        if (!node.isOpen && !node.isChecked && !node.isSolid) {
            node.isOpen = true;
            node.parent = this.currentNode;
            this.openList.add(node);
        }
    }

    private void trackDestinationPath() {
        // Backtrack from destination to start
        Node current = this.destinationNode;
        while (current != this.startNode) {
            this.pathList.addFirst(current);
            current = current.parent;
        }
    }
}
