package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import enums.Direction;
import enums.ObjectTyp;
import main.GamePanel;
import main.Keyboard;
import main.UtilityTool;
import tile.Tile;

public class Player extends Entity {
    private final GamePanel gamePanel;
    private final Keyboard keyboard;
    private final int playerVelocity = 4;
    private final int playerCollisionOffset = 8;
    public final int screenX;
    public final int screenY;

    public int countPickedUpKeys;

    public Player(GamePanel p, Keyboard k) {
        this.gamePanel = p;
        this.keyboard = k;
        this.screenX = (this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize / 2);
        this.screenY = (this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize / 2);

        this.setDefaultValues();
        this.getPlayerImages();
    }

    private void setDefaultValues() {
        // set player into center
        this.worldX = (this.gamePanel.worldWidth / 2) - (this.gamePanel.tileSize / 2);
        this.worldY = (this.gamePanel.worldHeight / 2) - (this.gamePanel.tileSize / 2);
        this.velocity = this.playerVelocity;
        // default player looks down
        this.direction = Direction.DOWN;
        // players solid area
        this.solidArea = new Rectangle(
            this.playerCollisionOffset,
            this.playerCollisionOffset * 2,
            this.gamePanel.tileSize - (this.playerCollisionOffset * 2),
            this.gamePanel.tileSize - (this.playerCollisionOffset * 2)
        );
        this.solidAreaDefaultX = this.playerCollisionOffset;
        this.solidAreaDefaultY = this.playerCollisionOffset * 2;
        // attributes
        this.countPickedUpKeys = 0;
    }

    private void getPlayerImages() {
        this.up1 = this.setupPlayerImage("boy_up_1");
        this.up2 = this.setupPlayerImage("boy_up_2");
        this.down1 = this.setupPlayerImage("boy_down_1");
        this.down2 = this.setupPlayerImage("boy_down_2");
        this.left1 = this.setupPlayerImage("boy_left_1");
        this.left2 = this.setupPlayerImage("boy_left_2");
        this.right1 = this.setupPlayerImage("boy_right_1");
        this.right2 = this.setupPlayerImage("boy_right_2");
    }

    private BufferedImage setupPlayerImage(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, this.gamePanel.tileSize, this.gamePanel.tileSize);
        }
        catch (IOException _) {}
        return image;
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
                    this.gamePanel.gui.showMessage("You picked up a key!");
                }
                case ObjectTyp.Door -> {
                    if(countPickedUpKeys > 0) {
                        this.gamePanel.objects.set(objectIndex, null);
                        this.countPickedUpKeys--;
                        this.gamePanel.gui.showMessage("You opened the door!");
                    }
                    else {
                        this.gamePanel.gui.showMessage("You need a key!");
                    }
                }
                case ObjectTyp.Chest -> {
                    this.gamePanel.objects.set(objectIndex, null);
                    this.gamePanel.gui.gameFinished = true;
                }
            }
        }
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
