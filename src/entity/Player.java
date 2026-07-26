package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import enums.Direction;
import main.GamePanel;
import main.Keyboard;

public class Player extends Entity {
    private final Keyboard keyboard;
    public int screenX;
    public int screenY;

    public Player(GamePanel p, Keyboard k) {
        super(p);
        this.keyboard = k;

        this.setDefaultValues();
        this.getPlayerImages();
    }

    private void setDefaultValues() {
        // centering player on screen
        this.screenX = (this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize / 2);
        this.screenY = (this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize / 2);
        // set player into center
        this.worldX = (this.gamePanel.worldWidth / 2) - (2 * this.gamePanel.tileSize);
        this.worldY = (this.gamePanel.worldHeight / 2) - (4 * this.gamePanel.tileSize);
        this.velocity = 4;
    }

    private void getPlayerImages() {
        this.up1 = this.setupEntityImage("/res/player/boy_up_1.png");
        this.up2 = this.setupEntityImage("/res/player/boy_up_2.png");
        this.down1 = this.setupEntityImage("/res/player/boy_down_1.png");
        this.down2 = this.setupEntityImage("/res/player/boy_down_2.png");
        this.left1 = this.setupEntityImage("/res/player/boy_left_1.png");
        this.left2 = this.setupEntityImage("/res/player/boy_left_2.png");
        this.right1 = this.setupEntityImage("/res/player/boy_right_1.png");
        this.right2 = this.setupEntityImage("/res/player/boy_right_2.png");
    }

    public void update() {
        if (this.keyboard.isUpPressed || this.keyboard.isDownPressed || this.keyboard.isLeftPressed || this.keyboard.isRightPressed) {
            if (this.keyboard.isUpPressed) { this.direction = Direction.UP; }
            else if (this.keyboard.isDownPressed) { this.direction = Direction.DOWN; }
            else if (this.keyboard.isLeftPressed) { this.direction = Direction.LEFT; }
            else if (this.keyboard.isRightPressed) { this.direction = Direction.RIGHT; }

            // check tile collision
            this.collisionDetected = false;
            this.gamePanel.collisionDetector.detectEntityCollisionWithTile(this);

            // check object collision
            int objectIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithObject(this, true);
            if (objectIndex != Integer.MAX_VALUE) {
                this.interactWithCollidedObject(objectIndex);
            }

            // check entity collision
            int entityIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithEntities(this);
            if (entityIndex != Integer.MAX_VALUE) {
                this.interactWithCollidedEntity(entityIndex);
            }

            if (!this.collisionDetected) {
                switch (this.direction) {
                    case Direction.UP -> { this.worldY -= this.velocity; }
                    case Direction.DOWN -> { this.worldY += this.velocity; }
                    case Direction.LEFT -> { this.worldX -= this.velocity; }
                    case Direction.RIGHT -> { this.worldX += this.velocity; }
                }
            }

            this.animationCounter++;
            // player image should change ever FPS / 4 = 15 frames
            if (this.animationCounter > (this.gamePanel.FPS / 4)) {
                this.animationFrame = (this.animationFrame == 1) ? 2 : 1;
                this.animationCounter = 0;
            }
        }
    }

    private void interactWithCollidedObject(int objectIndex) {

    }

    private void interactWithCollidedEntity(int entityIndex) {

    }

    public void draw(Graphics2D g2) {
        BufferedImage playerImage = this.getPlayersAnimationFrameImage();
        g2.drawImage(playerImage, this.screenX, this.screenY, null);
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
