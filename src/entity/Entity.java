package entity;

import enums.Direction;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Entity {
    public GamePanel gamePanel;

    public int worldX, worldY;
    public int velocity;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    public int animationCounter = 0;
    public int animationFrame = 1;
    public int actionCounterFrames = 0;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionDetected = false;

    public Entity(GamePanel p) {
        this.gamePanel = p;

        // entity solid area
        int entityCollisionOffset = 8;
        this.solidArea = new Rectangle(
                entityCollisionOffset,
                entityCollisionOffset * 2,
                this.gamePanel.tileSize - (entityCollisionOffset * 2),
                this.gamePanel.tileSize - (entityCollisionOffset * 2)
        );
        this.solidAreaDefaultX = entityCollisionOffset;
        this.solidAreaDefaultY = entityCollisionOffset * 2;
        // default entity looks down
        this.direction = Direction.DOWN;
    }

    public void setAction() {}

    public void update() {
        this.setAction();

        // check collision
        this.collisionDetected = false;
        this.gamePanel.collisionDetector.detectEntityCollisionWithTile(this);
        this.gamePanel.collisionDetector.detectEntityCollisionWithObject(this, false);
        this.gamePanel.collisionDetector.detectEntityCollisionWithPlayer(this);

        if (!this.collisionDetected) {
            switch (this.direction) {
                case Direction.UP -> { this.worldY -= this.velocity; }
                case Direction.DOWN -> { this.worldY += this.velocity; }
                case Direction.LEFT -> { this.worldX -= this.velocity; }
                case Direction.RIGHT -> { this.worldX += this.velocity; }
            }
        }

        this.animationCounter++;
        // entity image should change ever FPS / 4 = 15 frames
        if (this.animationCounter > (this.gamePanel.FPS / 4)) {
            this.animationFrame = (this.animationFrame == 1) ? 2 : 1;
            this.animationCounter = 0;
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
            BufferedImage image = null;
            switch (this.direction) {
                case Direction.UP -> { image = (this.animationFrame == 1) ? this.up1 : this.up2; }
                case Direction.DOWN -> { image = (this.animationFrame == 1) ? this.down1 : this.down2; }
                case Direction.LEFT -> { image = (this.animationFrame == 1) ? this.left1 : this.left2; }
                case Direction.RIGHT -> { image = (this.animationFrame == 1) ? this.right1 : this.right2; }
            }
            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }

    public BufferedImage setupEntityImage(String filePath) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage entityImage = null;
        try {
            entityImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
            entityImage = utilityTool.scaleImage(entityImage, this.gamePanel.tileSize, this.gamePanel.tileSize);
        }
        catch (IOException _) {}
        return entityImage;
    }
}
