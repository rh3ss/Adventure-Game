package tileFix;

import main.GamePanel;

import java.awt.*;

public class TreePine extends TileFix {

    public TreePine(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/tilesFix/tree_pine.png", gamePanel.tileSize, gamePanel.tileSize * 3);
        this.isSolid = true;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = (worldRow * gamePanel.tileSize) - gamePanel.tileSize * 2;

        int entityCollisionOffset = 8;
        this.solidArea = new Rectangle(
                entityCollisionOffset,
                entityCollisionOffset * 2,
                gamePanel.tileSize - (entityCollisionOffset * 2),
                (gamePanel.tileSize * 2) - (entityCollisionOffset * 2)
        );
        this.solidAreaDefaultX = entityCollisionOffset;
        this.solidAreaDefaultY = entityCollisionOffset * 6;
    }
}
