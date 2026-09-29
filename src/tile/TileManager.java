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
        setupTiles(00, "000", false);
        setupTiles(1, "000", false);
        setupTiles(2, "000", false);
        setupTiles(3, "000", false);
        setupTiles(4, "000", false);
        setupTiles(5, "000", false);
        setupTiles(6, "000", false);
        setupTiles(7, "000", false);
        setupTiles(8, "000", false);
        setupTiles(9, "000", false);
        // grass
        setupTiles(10, "001", false);
        setupTiles(11, "002", false);
        // water
        setupTiles(12, "water_clear", true);
        setupTiles(13, "water_waves", true);
        setupTiles(14, "water_north_west", true);
        setupTiles(15, "water_north", true);
        setupTiles(16, "water_north_east", true);
        setupTiles(17, "water_west", true);
        setupTiles(18, "water_east", true);
        setupTiles(19, "water_south_west", true);
        setupTiles(20, "water_south", true);
        setupTiles(21, "water_south_east", true);
        setupTiles(22, "water_top_left", true);
        setupTiles(23, "water_top_right", true);
        setupTiles(24, "water_bottom_left", true);
        setupTiles(25, "water_bottom_left", true);
        // path
        setupTiles(26, "003", false);
        setupTiles(27, "004", false);
        setupTiles(28, "005", false);
        setupTiles(29, "006", false);
        setupTiles(30, "007", false);
        setupTiles(31, "008", false);
        setupTiles(32, "009", false);
        setupTiles(33, "010", false);
        setupTiles(34, "011", false);
        setupTiles(35, "012", false);
        setupTiles(36, "013", false);
        setupTiles(37, "014", false);
        setupTiles(38, "015", false);
        // environment
        setupTiles(39, "017", false);
        setupTiles(40, "032", true);
        setupTiles(41, "016_1", true);
        // indoor
        setupTiles(42, "033", false);
        setupTiles(43, "034", false);
        setupTiles(44, "035", true);
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
