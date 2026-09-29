package tileFix;

import main.GamePanel;

import java.awt.Rectangle;

public class BigTree extends TileFix {

    public BigTree(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/tilesFix/big_tree.png", gamePanel.tileSize, gamePanel.tileSize * 2);
        this.isSolid = true;
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
