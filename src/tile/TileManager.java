package tile;

import java.awt.Graphics2D;
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
    public final int[][] mapTileNumbers;

    public TileManager(GamePanel p) {
        this.gamePanel = p;
        this.tiles = new HashMap<>();
        this.mapTileNumbers = new int[this.gamePanel.maxWorldColumns][this.gamePanel.maxWorldRows];

        this.getTileImages();
        this.loadMap("/res/maps/world02.txt");
    }

    private void getTileImages() {
        // placeholders
        setupTiles(0, "000", false);
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
        setupTiles(12, "018", true);
        setupTiles(13, "019", true);
        setupTiles(14, "020", true);
        setupTiles(15, "021", true);
        setupTiles(16, "022", true);
        setupTiles(17, "023", true);
        setupTiles(18, "024", true);
        setupTiles(19, "025", true);
        setupTiles(20, "026", true);
        setupTiles(21, "027", true);
        setupTiles(22, "028", true);
        setupTiles(23, "029", true);
        setupTiles(24, "030", true);
        setupTiles(25, "031", true);
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
        setupTiles(41, "016", true);
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

    private void loadMap(String filePath) {
        try {
            InputStream stream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            for (int row = 0; row < this.gamePanel.maxWorldRows; row++) {
                String[] rowNumbers = reader.readLine().split(" ");
                for (int col = 0; col < this.gamePanel.maxWorldColumns; col++) {
                    this.mapTileNumbers[col][row] = Integer.parseInt(rowNumbers[col]);
                }
            }
        } 
        catch (IOException _) {}
    }

    public void draw(Graphics2D g2) {
        for (int row = 0; row < this.gamePanel.maxWorldRows; row++) {
            for (int col = 0; col < this.gamePanel.maxWorldColumns; col++) {
                int tileNumber = this.mapTileNumbers[col][row];
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
    }
}
