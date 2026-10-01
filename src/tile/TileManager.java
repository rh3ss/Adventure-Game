package tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {
    private final GamePanel gamePanel;
    public final HashMap<Integer, Tile> tiles;
    public final int[][][] mapTileNumbers;
    public boolean drawTrackingPath;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new HashMap<>();
        this.mapTileNumbers = new int[gamePanel.maxNumberOfMaps][gamePanel.maxWorldColumns][gamePanel.maxWorldRows];
        this.drawTrackingPath = true;

        this.getTileImages();
        this.loadMap("/res/maps/world.txt", 0);
        this.loadMap("/res/maps/hut.txt", 1);
        this.loadMap("/res/maps/farm_barn.txt", 2);
    }

    private void getTileImages() {
        // placeholders
        setupTiles(0, "black", true);
        setupTiles(1, "black", true);
        setupTiles(2, "black", true);
        setupTiles(3, "black", true);
        setupTiles(4, "black", true);
        setupTiles(5, "black", true);
        setupTiles(6, "black", true);
        setupTiles(7, "black", true);
        setupTiles(8, "black", true);
        setupTiles(9, "black", true);
        // grass
        setupTiles(10, "grass_clear", false);
        setupTiles(11, "grass_big_flower", false);
        setupTiles(15, "dirt", false);
        setupTiles(20, "stone_small", true);
        // water
        setupTiles(30, "water_clear", true);
        setupTiles(31, "water_waves", true);
        setupTiles(32, "water_north_west", true);
        setupTiles(33, "water_north", true);
        setupTiles(34, "water_north_east", true);
        setupTiles(35, "water_west", true);
        setupTiles(36, "water_east", true);
        setupTiles(37, "water_south_west", true);
        setupTiles(38, "water_south", true);
        setupTiles(39, "water_south_east", true);
        setupTiles(40, "water_top_left", true);
        setupTiles(41, "water_top_right", true);
        setupTiles(42, "water_bottom_left", true);
        setupTiles(43, "water_bottom_right", true);
//        // path
//        setupTiles(26, "003", false);
//        setupTiles(27, "004", false);
//        setupTiles(28, "005", false);
//        setupTiles(29, "006", false);
//        setupTiles(30, "007", false);
//        setupTiles(31, "008", false);
//        setupTiles(32, "009", false);
//        setupTiles(33, "010", false);
//        setupTiles(34, "011", false);
//        setupTiles(35, "012", false);
//        setupTiles(36, "013", false);
//        setupTiles(37, "014", false);
//        setupTiles(38, "015", false);
        // walls
        setupTiles(70, "wall_stone", true);
        // floor
        setupTiles(80, "wood_planks", false);
        // indoor
        setupTiles(90, "hut", false);
        setupTiles(95, "table", true);
    }

    private void setupTiles(int keyIndex, String imageName, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imageName + ".png"));
            BufferedImage scaledImage = utilityTool.scaleImage(originalImage, this.gamePanel.tileSize, this.gamePanel.tileSize);
            this.tiles.put(keyIndex, new Tile(scaledImage, collision));
        }
        catch (IOException _) {}
    }

    private void loadMap(String filePath, int mapNumber) {
        try {
            InputStream stream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            for (int row = 0; row < this.gamePanel.maxWorldRows; row++) {
                String[] rowNumbers = reader.readLine().split(" ");
                for (int col = 0; col < this.gamePanel.maxWorldColumns; col++) {
                    this.mapTileNumbers[mapNumber][col][row] = Integer.parseInt(rowNumbers[col]);
                }
            }
        } 
        catch (IOException _) {}
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < this.gamePanel.maxWorldRows; row++) {
            for (int col = 0; col < this.gamePanel.maxWorldColumns; col++) {
                int tileNumber = this.mapTileNumbers[this.gamePanel.currentMapNumber][col][row];
                int worldX = col * this.gamePanel.tileSize;
                int worldY = row * this.gamePanel.tileSize;
                int screenX = worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
                int screenY = worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;

                // draw only tiles in players field of view
                if (worldX + this.gamePanel.tileSize > this.gamePanel.player.worldX - this.gamePanel.player.screenX &&
                        worldX - this.gamePanel.tileSize < this.gamePanel.player.worldX + this.gamePanel.player.screenX &&
                        worldY + this.gamePanel.tileSize > this.gamePanel.player.worldY - this.gamePanel.player.screenY &&
                        worldY - this.gamePanel.tileSize < this.gamePanel.player.worldY + this.gamePanel.player.screenY) {
                    g2.drawImage(this.tiles.get(tileNumber).image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
                }
            }
        }
        // draw the tracking path
        if (this.drawTrackingPath) {
            g2.setColor(new Color(255, 255, 255, 75));
            for (int idx = 0; idx < this.gamePanel.pathFinder.pathList.size(); ++idx) {
                int worldX = this.gamePanel.pathFinder.pathList.get(idx).column * this.gamePanel.tileSize;
                int worldY = this.gamePanel.pathFinder.pathList.get(idx).row * this.gamePanel.tileSize;
                int screenX = worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
                int screenY = worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;
                g2.fillRect(screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize);
            }
        }
    }
}
