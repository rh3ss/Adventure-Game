package tileInteractive;

import entity.Entity;
import enums.EntityType;
import main.GamePanel;

import java.awt.Graphics2D;

public class InteractiveTile extends Entity {
    public final GamePanel gamePanel;
    public boolean isDestructible;

    public InteractiveTile(GamePanel p, int worldColumn, int worldRow) {
        super(p);
        this.gamePanel = p;

        this.entityType = EntityType.INTERACTIVE_TILE;
        this.isDestructible = false;
    }

    public boolean isCorrectObjectEquipped(Entity user) { return false; }

    public InteractiveTile getFollowingTileAfterDestruction() { return null; }

    public void update() {
        if (this.isInvincible) {
            this.invincibleCounterFrames++;
            if (this.invincibleCounterFrames > (this.gamePanel.FPS - 20)) {
                this.isInvincible = false;
                this.invincibleCounterFrames = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;
        // draw only entities in players field of view
        if( this.worldX + this.gamePanel.tileSize > this.gamePanel.player.worldX - this.gamePanel.player.screenX &&
                this.worldX - this.gamePanel.tileSize < this.gamePanel.player.worldX + this.gamePanel.player.screenX &&
                this.worldY + this.gamePanel.tileSize > this.gamePanel.player.worldY - this.gamePanel.player.screenY &&
                this.worldY - this.gamePanel.tileSize < this.gamePanel.player.worldY + this.gamePanel.player.screenY
        ) {
            g2.drawImage(this.down1, screenX, screenY, null);
        }
    }
}
