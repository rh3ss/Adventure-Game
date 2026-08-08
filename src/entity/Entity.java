package entity;

import enums.Direction;
import enums.EntityType;
import enums.MonsterType;
import enums.ObjectType;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Entity {
    public GamePanel gamePanel;

    // IMAGES
    public BufferedImage image1, image2;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;

    // INTERACTION
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle attackArea;
    public ArrayList<String> dialogues;
    public int dialogueIndex;

    // STATE
    public int worldX, worldY;
    public Direction direction;
    public int animationFrame = 1;
    public boolean isInvincible = false;
    public boolean isAttacking = false;
    public boolean collisionDetected = false;

    // COUNTER
    public int animationCounterFrames = 0;
    public int actionCounterFrames = 0;
    public int invincibleCounterFrames = 0;

    // ATTRIBUTES
    public EntityType entityType;
    public ObjectType objectType;
    public MonsterType monsterType;
    public boolean isSolid = false;
    public int velocity, maxHearts, currentHearts, attackDamage;

    public Entity(GamePanel p) {
        this.gamePanel = p;
        this.dialogues = new ArrayList<>();
        this.dialogueIndex = 0;

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

    public void speak() {
        this.gamePanel.gui.currentDialogueMessage = this.dialogues.get(this.dialogueIndex);
        this.dialogueIndex++;
        if (this.dialogueIndex > this.dialogues.size() - 1) {
            this.dialogueIndex = 0;
        }
        // entity should look in players direction while dialogue
        switch (this.gamePanel.player.direction) {
            case Direction.UP -> { this.direction = Direction.DOWN; }
            case Direction.DOWN -> { this.direction = Direction.UP; }
            case Direction.LEFT -> { this.direction = Direction.RIGHT; }
            case Direction.RIGHT -> { this.direction = Direction.LEFT; }
        }
    }

    public void update() {
        this.setAction();

        // check collision
        this.collisionDetected = false;
        this.gamePanel.collisionDetector.detectEntityCollisionWithTile(this);
        this.gamePanel.collisionDetector.detectEntityCollisionWithEntities(this);
        boolean entityCollidedWithPlayer = this.gamePanel.collisionDetector.detectEntityCollisionWithPlayer(this);

        if (this.entityType == EntityType.MONSTER && entityCollidedWithPlayer) {
            if (!this.gamePanel.player.isInvincible && this.gamePanel.player.currentHearts > 0) {
                this.gamePanel.player.currentHearts--;
                this.gamePanel.player.isInvincible = true;
            }
        }

        if (!this.collisionDetected) {
            switch (this.direction) {
                case Direction.UP -> { this.worldY -= this.velocity; }
                case Direction.DOWN -> { this.worldY += this.velocity; }
                case Direction.LEFT -> { this.worldX -= this.velocity; }
                case Direction.RIGHT -> { this.worldX += this.velocity; }
            }
        }

        this.animationCounterFrames++;
        // entity image should change ever FPS / 4 = 15 frames
        if (this.animationCounterFrames > (this.gamePanel.FPS / 4)) {
            this.animationFrame = (this.animationFrame == 1) ? 2 : 1;
            this.animationCounterFrames = 0;
        }

        if (isInvincible) {
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
            BufferedImage image = null;
            if (this.entityType != EntityType.OBJECT) {
                switch (this.direction) {
                    case Direction.UP -> { image = (this.animationFrame == 1) ? this.up1 : this.up2; }
                    case Direction.DOWN -> { image = (this.animationFrame == 1) ? this.down1 : this.down2; }
                    case Direction.LEFT -> { image = (this.animationFrame == 1) ? this.left1 : this.left2; }
                    case Direction.RIGHT -> { image = (this.animationFrame == 1) ? this.right1 : this.right2; }
                }
            }
            else {
                image = this.down1;
            }
            if (this.isInvincible) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            }
            g2.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public BufferedImage setupEntityImage(String filePath, int width, int height) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage entityImage = null;
        try {
            entityImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(filePath)));
            entityImage = utilityTool.scaleImage(entityImage, width, height);
        }
        catch (IOException _) {}
        return entityImage;
    }
}
