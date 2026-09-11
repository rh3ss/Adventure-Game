package tileInteractive;

import enums.InteractiveTileType;
import main.GamePanel;

import java.awt.Rectangle;

public class InteractiveTileTrunk extends InteractiveTile{

    public InteractiveTileTrunk(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.TRUNK;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/trunk.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;

        this.solidArea = new Rectangle(0, 0, 0, 0);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
