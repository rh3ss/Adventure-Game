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
        this.loadMap("/res/maps/world01.txt");
    }

    private void getTileImages() {
        setupTiles(0, "grass", false);
        setupTiles(1, "wall", true);
        setupTiles(2, "water", true);
        setupTiles(3, "earth", false);
        setupTiles(4, "tree", true);
        setupTiles(5, "sand", false);
    }

    private void setupTiles(int keyIndex, String imagePath, boolean collision) {
        UtilityTool utilityTool = new UtilityTool();
        try {
            BufferedImage originalImage = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + imagePath + ".png"));
            BufferedImage scaledImage = utilityTool.scaleImage(originalImage, this.gamePanel.tileSize, this.gamePanel.tileSize);
            this.tiles.put(keyIndex, new Tile(scaledImage, collision));
        }
        catch (IOException e) {}
    }

    private void loadMap(String filePath) {
        try {
            InputStream stream = getClass().getResourceAsStream(filePath);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            for(int row = 0 ; row < this.gamePanel.maxWorldRows ; row++) {
                String[] rowNumbers = reader.readLine().split(" ");
                for(int column = 0 ; column < this.gamePanel.maxWorldColumns ; column++) {
                    int number = Integer.parseInt(rowNumbers[column]);
                    this.mapTileNumbers[column][row] = number;
                }
            }
        } 
        catch (IOException e) {}
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
                if( worldX + this.gamePanel.tileSize > this.gamePanel.player.worldX - this.gamePanel.player.screenX &&
                        worldX - this.gamePanel.tileSize < this.gamePanel.player.worldX + this.gamePanel.player.screenX &&
                        worldY + this.gamePanel.tileSize > this.gamePanel.player.worldY - this.gamePanel.player.screenY &&
                        worldY - this.gamePanel.tileSize < this.gamePanel.player.worldY + this.gamePanel.player.screenY
                )  {
                    g2.drawImage(this.tiles.get(tileNumber).image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
                }
            }
        }
    }
}
