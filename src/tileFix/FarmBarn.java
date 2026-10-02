package tileFix;

import main.GamePanel;

import java.awt.Rectangle;

public class FarmBarn extends TileFix {

    public FarmBarn(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize - 10;

        this.down1 = this.setupEntityImage("/res/tilesFix/farm_barn.png", gamePanel.tileSize * 5, gamePanel.tileSize * 5);
        this.isSolid = true;
        int entityCollisionOffset = 16;
        this.solidArea = new Rectangle(
                entityCollisionOffset,
                entityCollisionOffset * 2,
                (gamePanel.tileSize * 5) - (entityCollisionOffset * 2),
                (gamePanel.tileSize * 4) - (entityCollisionOffset)
        );
        this.solidAreaDefaultX = entityCollisionOffset;
        this.solidAreaDefaultY = (int) (entityCollisionOffset * 2.5);
    }
}
