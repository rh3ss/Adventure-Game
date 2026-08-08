package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

import enums.Direction;
import enums.EntityType;
import enums.GameState;
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
        this.getImages();
    }

    private void setDefaultValues() {
        this.entityType = EntityType.PLAYER;
        // centering player on screen
        this.screenX = (this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize / 2);
        this.screenY = (this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize / 2);
        // set player into center
        this.worldX = (this.gamePanel.worldWidth / 2);
        this.worldY = (this.gamePanel.worldHeight / 2);
        this.velocity = 4;
        // heart status
        this.maxHearts = 5;
        this.currentHearts = this.maxHearts;
        // attacking
        this.attackDamage = 1;
        this.attackArea = new Rectangle(0, 0, 36, 36);
    }

    private void getImages() {
        // MOVEMENT
        this.up1 = this.setupEntityImage("/res/player/boy_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/player/boy_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/player/boy_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/player/boy_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/player/boy_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/player/boy_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/player/boy_right_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/player/boy_right_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        // ATTACK
        this.attackUp1 = this.setupEntityImage("/res/player/boy_attack_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
        this.attackUp2 = this.setupEntityImage("/res/player/boy_attack_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
        this.attackDown1 = this.setupEntityImage("/res/player/boy_attack_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
        this.attackDown2 = this.setupEntityImage("/res/player/boy_attack_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
        this.attackLeft1 = this.setupEntityImage("/res/player/boy_attack_left_1.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
        this.attackLeft2 = this.setupEntityImage("/res/player/boy_attack_left_2.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
        this.attackRight1 = this.setupEntityImage("/res/player/boy_attack_right_1.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
        this.attackRight2 = this.setupEntityImage("/res/player/boy_attack_right_2.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
    }

    public void update() {
        // ATTACKING
        if (this.isAttacking) {
            playerIsAttacking();
        }
        // MOVEMENT
        else if (this.keyboard.isUpPressed || this.keyboard.isDownPressed || this.keyboard.isLeftPressed || this.keyboard.isRightPressed || this.keyboard.isEnterPressed) {
            playerIsMoving();
        }
        if (isInvincible) {
            this.invincibleCounterFrames++;
            if (this.invincibleCounterFrames > this.gamePanel.FPS) {
                this.isInvincible = false;
                this.invincibleCounterFrames = 0;
            }
        }
    }

    private void playerIsAttacking() {
        this.animationCounterFrames++;
        // charging attack for FPS / 10 = 6 Frames and launch attack for FPS / 2 = 30 Frames
        if (this.animationCounterFrames < (this.gamePanel.FPS / 10)) {
            this.animationFrame = 1;
        }
        else if (this.animationCounterFrames < (this.gamePanel.FPS / 2)) {
            this.animationFrame = 2;

            int currentWorldX = this.worldX, currentWorldY = this.worldY;
            int currentSolidAreaWidth = this.solidArea.width, currentSolidAreaHeight = this.solidArea.height;

            switch (this.direction) {
                case Direction.UP -> { this.worldY -= this.attackArea.height; }
                case Direction.DOWN -> { this.worldY += this.attackArea.height; }
                case Direction.LEFT -> { this.worldX -= this.attackArea.width; }
                case Direction.RIGHT -> { this.worldX += this.attackArea.width; }
            }
            this.solidArea.width = attackArea.width;
            this.solidArea.height = attackArea.height;
            
            int entityIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithEntities(this);
            this.playerAttacksMonster(entityIndex);

            this.worldX = currentWorldX;
            this.worldY = currentWorldY;
            this.solidArea.width = currentSolidAreaWidth;
            this.solidArea.height = currentSolidAreaHeight;
        }
        if (this.animationCounterFrames > (this.gamePanel.FPS / 2)) {
            this.animationFrame = 1;
            this.animationCounterFrames = 0;
            this.isAttacking = false;
        }
    }

    private void playerIsMoving() {
        if (this.keyboard.isUpPressed) { this.direction = Direction.UP; }
        else if (this.keyboard.isDownPressed) { this.direction = Direction.DOWN; }
        else if (this.keyboard.isLeftPressed) { this.direction = Direction.LEFT; }
        else if (this.keyboard.isRightPressed) { this.direction = Direction.RIGHT; }

        // check tile collision
        this.collisionDetected = false;
        this.gamePanel.collisionDetector.detectEntityCollisionWithTile(this);
        // check entity collision
        int entityIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithEntities(this);
        this.interactWithCollidedEntity(entityIndex);
        // check event handling
        this.gamePanel.eventHandler.checkEvent();

        if (!this.collisionDetected && !this.keyboard.isEnterPressed) {
            switch (this.direction) {
                case Direction.UP -> { this.worldY -= this.velocity; }
                case Direction.DOWN -> { this.worldY += this.velocity; }
                case Direction.LEFT -> { this.worldX -= this.velocity; }
                case Direction.RIGHT -> { this.worldX += this.velocity; }
            }
        }
        this.gamePanel.keyboard.isEnterPressed = false;

        this.animationCounterFrames++;
        // player image should change ever FPS / 4 = 15 frames
        if (this.animationCounterFrames > (this.gamePanel.FPS / 4)) {
            this.animationFrame = (this.animationFrame == 1) ? 2 : 1;
            this.animationCounterFrames = 0;
        }
    }

    private void interactWithCollidedEntity(int entityIndex) {
        if (entityIndex != Integer.MAX_VALUE) {
            Entity entity = this.gamePanel.entities.get(entityIndex);
            switch (entity.entityType) {
                case EntityType.NPC -> { playerCollisionWithNPC(entity); }
                case EntityType.MONSTER -> { playerCollisionWithMonster(entity); }
                case EntityType.OBJECT -> { }
            }
        }
        else {
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.isAttacking = true;
            }
        }
    }

    private void playerCollisionWithNPC(Entity entity) {
        if (this.gamePanel.keyboard.isEnterPressed) {
            this.gamePanel.gameState = GameState.DIALOGUE;
            entity.speak();
        }
    }

    private void playerCollisionWithMonster(Entity entity) {
        if (!this.isInvincible && this.currentHearts > 0) {
            this.currentHearts -= entity.attackDamage;
            this.isInvincible = true;
        }
    }

    private void playerAttacksMonster(int entityIndex) {
        if (entityIndex != Integer.MAX_VALUE) {
            Entity monster = this.gamePanel.entities.get(entityIndex);
            if (monster.entityType == EntityType.MONSTER && !monster.isInvincible) {
                monster.currentHearts -= this.attackDamage;
                monster.isInvincible = true;
                if (monster.currentHearts < 1) {
                    this.gamePanel.entities.set(entityIndex, null);
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        // change screen pos for wider attack images
        int tempScreenX = this.screenX, tempScreenY = this.screenY;
        if (this.isAttacking && this.direction == Direction.UP) { tempScreenY -= this.gamePanel.tileSize; }
        else if (this.isAttacking && this.direction == Direction.LEFT) { tempScreenX -= this.gamePanel.tileSize; }

        if (this.isInvincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        BufferedImage playerImage = this.getPlayersAnimationFrameImage();
        g2.drawImage(playerImage, tempScreenX, tempScreenY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    private BufferedImage getPlayersAnimationFrameImage() {
        switch (this.direction) {
            case Direction.UP -> {
                return (!this.isAttacking) ? (this.animationFrame == 1) ? this.up1 : this.up2 : (this.animationFrame == 1) ? this.attackUp1 : this.attackUp2;
            }
            case Direction.DOWN -> {
                return (!this.isAttacking) ? (this.animationFrame == 1) ? this.down1 : this.down2 : (this.animationFrame == 1) ? this.attackDown1 : this.attackDown2;
            }
            case Direction.LEFT -> {
                return (!this.isAttacking) ? (this.animationFrame == 1) ? this.left1 : this.left2 : (this.animationFrame == 1) ? this.attackLeft1 : this.attackLeft2;
            }
            case Direction.RIGHT -> {
                return (!this.isAttacking) ? (this.animationFrame == 1) ? this.right1 : this.right2 : (this.animationFrame == 1) ? this.attackRight1 : this.attackRight2;
            }
        }
        return null;
    }
}
