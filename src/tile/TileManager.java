package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
    private final GamePanel gamePanel;
    public final Tile[] tile;
    public final int[][] mapTileNumbers;

    public TileManager(GamePanel p) {
        this.gamePanel = p;
        this.tile = new Tile[10];
        this.mapTileNumbers = new int[this.gamePanel.maxWorldColumns][this.gamePanel.maxWorldRows];

        this.getTileImage();
        this.loadMap("/res/maps/world01.txt");
    }

    private void getTileImage() {
        try {
            this.tile[0] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png")), false);
            this.tile[1] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png")), true);
            this.tile[2] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/water.png")), true);
            this.tile[3] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/earth.png")), false);
            this.tile[4] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/tree.png")), true);
            this.tile[5] = new Tile(ImageIO.read(getClass().getResourceAsStream("/res/tiles/sand.png")), false);
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
                    g2.drawImage(this.tile[tileNumber].image, screenX, screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
                }
            }
        }
    }
}
