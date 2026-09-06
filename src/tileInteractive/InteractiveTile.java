package tileInteractive;

import entity.Entity;
import enums.EntityType;
import main.GamePanel;

public class InteractiveTile extends Entity {
    public final GamePanel gamePanel;
    public boolean isDestructible;

    public InteractiveTile(GamePanel p, int worldColumn, int worldRow) {
        super(p);
        this.gamePanel = p;

        this.entityType = EntityType.INTERACTIVE_TILE;
        this.isDestructible = false;
    }

    public boolean isCorrectObjectEquipped(Entity user) {
        return false;
    }

    public InteractiveTile getFollowingTileAfterDestruction() {
        return null;
    }

    public void update() {
        if (isInvincible) {
            this.invincibleCounterFrames++;
            if (this.invincibleCounterFrames > (this.gamePanel.FPS - 40)) {
                this.isInvincible = false;
                this.invincibleCounterFrames = 0;
            }
        }
    }
}
