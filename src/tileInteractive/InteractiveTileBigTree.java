package tileInteractive;

import enums.InteractiveTileType;
import main.GamePanel;

import java.awt.Rectangle;

public class InteractiveTileBigTree extends InteractiveTile {

    public InteractiveTileBigTree(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.BIG_TREE;
        this.isSolid = true;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/big_tree.png", gamePanel.tileSize, gamePanel.tileSize * 2);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = (worldRow * gamePanel.tileSize) - gamePanel.tileSize;

        int entityCollisionOffset = 8;
        this.solidArea = new Rectangle(
                entityCollisionOffset,
                entityCollisionOffset * 2,
                gamePanel.tileSize - (entityCollisionOffset * 2),
                gamePanel.tileSize - (entityCollisionOffset * 2)
        );
        this.solidAreaDefaultX = entityCollisionOffset;
        this.solidAreaDefaultY = entityCollisionOffset * 6;
    }
}
