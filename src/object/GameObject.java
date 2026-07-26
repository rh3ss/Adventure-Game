package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import enums.ObjectTyp;
import main.GamePanel;
import main.UtilityTool;

public class GameObject {
    public BufferedImage image;
    public String name;
    public ObjectTyp typ;
    public boolean isSolid = false;
    public int worldX, worldY;

    public UtilityTool utilityTool = new UtilityTool();
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        int screenX = this.worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = this.worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        // draw only objects in players field of view
        if( this.worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                this.worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                this.worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                this.worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
        ) {
            g2.drawImage(this.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
