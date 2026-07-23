package object;

import enums.ObjectTyp;
import main.GamePanel;
import main.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    public UtilityTool utilityTool = new UtilityTool();
    public BufferedImage image;
    public String name;
    public ObjectTyp typ;
    public boolean isSolid = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
        // draw only objects in players field of view
        if( worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
        ) {
            g2.drawImage(this.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
