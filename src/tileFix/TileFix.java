package tileFix;

import entity.Entity;
import main.GamePanel;

import java.awt.Graphics2D;

public abstract class TileFix extends Entity {

    public TileFix(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }

    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;
        g2.drawImage(this.down1, screenX, screenY, null);
    }
}
