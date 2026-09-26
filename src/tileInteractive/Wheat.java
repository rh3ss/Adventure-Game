package tileInteractive;

import enums.InteractiveTileType;
import main.GamePanel;


public class Wheat extends InteractiveTile {

    public Wheat(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.WHEAT;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/wheat.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
