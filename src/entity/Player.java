package entity;

import enums.Direction;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import enums.ObjectTyp;
import main.GamePanel;
import main.Keyboard;

public class Player extends Entity {
    private final GamePanel gamePanel;
    private final Keyboard keyboard;
    private final int playerVelocity = 4;
    private final int playerCollisionOffset = 8;

    private int countPickedUpKeys;
    public final int screenX;
    public final int screenY;  

    public Player(GamePanel p, Keyboard k) {
        this.gamePanel = p;
        this.keyboard = k;
        this.screenX = (this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize / 2);
        this.screenY = (this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize / 2);

        this.solidArea = new Rectangle(
            this.playerCollisionOffset, 
            this.playerCollisionOffset * 2, 
            this.gamePanel.tileSize - (this.playerCollisionOffset * 2),
            this.gamePanel.tileSize - (this.playerCollisionOffset * 2)
        );
        this.solidAreaDefaultX = this.playerCollisionOffset;
        this.solidAreaDefaultY = this.playerCollisionOffset * 2;

        this.setDefaultValues();
        this.getPlayerImage();
    }

    private void setDefaultValues() {
        // set player into center
        this.worldX = (this.gamePanel.worldWidth / 2) - (this.gamePanel.tileSize / 2);
        this.worldY = (this.gamePanel.worldHeight / 2) - (this.gamePanel.tileSize / 2);
        this.velocity = this.playerVelocity;
        // default player looks down
        this.direction = Direction.DOWN;
        // attributes
        this.countPickedUpKeys = 0;
    }

    private void getPlayerImage() {
        try {
            this.up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_1.png"));
            this.up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_up_2.png"));
            this.down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_1.png"));
            this.down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_down_2.png"));
            this.left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_1.png"));
            this.left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_left_2.png"));
            this.right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_1.png"));
            this.right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/boy_right_2.png"));
        } 
        catch (IOException e) {}
    }

    public void update() {
        if(this.keyboard.isUpPressed || this.keyboard.isDownPressed || this.keyboard.isLeftPressed || this.keyboard.isRightPressed) {
            if(this.keyboard.isUpPressed) { this.direction = Direction.UP; }
            else if(this.keyboard.isDownPressed) { this.direction = Direction.DOWN; }
            else if(this.keyboard.isLeftPressed) { this.direction = Direction.LEFT; }
            else if(this.keyboard.isRightPressed) { this.direction = Direction.RIGHT; }

            // check tile collision
            this.collisionDetected = false;
            this.gamePanel.collisionDetector.detectEntityCollisionWithTile(this);
            // check object collision
            int objectIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithObject(this, true);
            this.interactWithCollidedObject(objectIndex);

            // if player hit non-solid tile, then he can move
            if(!this.collisionDetected) {
                switch (this.direction) {
                    case Direction.UP -> { this.worldY -= this.velocity; }
                    case Direction.DOWN -> { this.worldY += this.velocity; }
                    case Direction.LEFT -> { this.worldX -= this.velocity; }
                    case Direction.RIGHT -> { this.worldX += this.velocity; }
                }
            }

            this.animationCounter++;
            // player image should change ever FPS / 4  = 15 frames 
            if(this.animationCounter > (this.gamePanel.FPS / 4)) {
                this.animationFrame = (this.animationFrame == 1) ? 2 : 1;
                this.animationCounter = 0;
            }
        }
    }

    public void interactWithCollidedObject(int objectIndex) {
        if(objectIndex != Integer.MAX_VALUE) {
            ObjectTyp objectTyp = this.gamePanel.objects.get(objectIndex).typ;
            switch (objectTyp) {
                case ObjectTyp.Key -> {
                    this.countPickedUpKeys++;
                    this.gamePanel.objects.set(objectIndex, null);
                }
                case ObjectTyp.Door -> {
                    if(countPickedUpKeys > 0) {
                        this.gamePanel.objects.set(objectIndex, null);
                        this.countPickedUpKeys--;
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage playerImage = this.getPlayersAnimationFrameImage();
        g2.drawImage(playerImage, this.screenX, this.screenY, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
    }

    private BufferedImage getPlayersAnimationFrameImage() {
        switch (this.direction) {
            case Direction.UP -> { return (this.animationFrame == 1) ? this.up1 : this.up2; }
            case Direction.DOWN -> { return (this.animationFrame == 1) ? this.down1 : this.down2; }
            case Direction.LEFT -> { return (this.animationFrame == 1) ? this.left1 : this.left2; }
            case Direction.RIGHT -> { return (this.animationFrame == 1) ? this.right1 : this.right2; }
        }
        return null;
    }
}
