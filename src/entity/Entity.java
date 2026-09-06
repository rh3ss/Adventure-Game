package entity;

import enums.*;
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
    public boolean isAlive = true;
    public boolean isDying = false;
    public boolean showHealthBar = false;
    public boolean collisionDetected = false;

    // COUNTER
    public int animationCounterFrames = 0;
    public int actionCounterFrames = 0;
    public int invincibleCounterFrames = 0;
    public int dyingCounterFrames = 0;
    public int healthBarCounterFrames = 0;
    public int shootingAvailableCounter = 0;

    // TYPES
    public EntityType entityType;
    public ObjectCategory objectCategory;
    public ObjectType objectType;
    public MonsterType monsterType;
    public InteractiveTileType interactiveTileType;

    // ATTRIBUTES
    public boolean isSolid = false;
    public int velocity, coins;
    public int projectileUsageCostValue;
    public int maxMana, currentMana;
    public int currentLevel, currentExperience, nextLevelExperience;
    public double strength, dexterity;
    public double maxHearts, currentHearts;
    public double attackDamage, defenseArmor;
    public Entity currentWeapon, currentShield;
    public Projectile currentProjectile;

    // OBJECT ATTRIBUTES
    public int objectBenefitValue;
    public double objectAttackDamageMultiplier, objectDamageReductionMultiplier;
    public String objectDescription = "";

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

    public void damageReaction() {}

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

    public void use(Entity entity) { }

    public void chooseObjectToDrop() { }

    public void dropObject(Entity objectToDrop) {
        objectToDrop.worldX = this.worldX;
        objectToDrop.worldY = this.worldY;
        this.gamePanel.entities.add(objectToDrop);
    }

    public void update() {
        this.setAction();

        // check collision
        this.collisionDetected = false;
        this.gamePanel.collisionDetector.detectEntityCollisionWithTile(this);
        this.gamePanel.collisionDetector.detectEntityCollisionWithObject(this, false);
        int entityIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithEntities(this);
        if (this.entityType == EntityType.PROJECTILE && entityIndex != Integer.MAX_VALUE) {
            this.collisionDetected = true;
        }

        boolean entityCollidedWithPlayer = this.gamePanel.collisionDetector.detectEntityCollisionWithPlayer(this);
        if (this.entityType == EntityType.MONSTER && entityCollidedWithPlayer) {
            this.entityDamagePlayer(this.attackDamage);
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
        if (this.shootingAvailableCounter < (this.gamePanel.FPS / 2)) {
            this.shootingAvailableCounter++;
        }
    }

    public void entityDamagePlayer(double attackDamage) {
        if (!this.gamePanel.player.isInvincible && this.gamePanel.player.currentHearts > 0) {
            double damage = attackDamage - this.gamePanel.player.defenseArmor;
            if (damage < 0) {
                damage = 0;
            }
            this.gamePanel.player.currentHearts -= damage;
            this.gamePanel.player.isInvincible = true;
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
            // draw current entity state
            if (this.entityType == EntityType.MONSTER && this.showHealthBar) {
                this.drawHealthBar(g2, screenX, screenY);
            }
            if (this.isInvincible) {
                this.showHealthBar = true;
                this.healthBarCounterFrames = 0;
                this.changeAlphaCompositeValue(g2, 0.5f);
            }
            if (this.isDying) {
                this.drawDyingAnimation(g2);
            }
            g2.drawImage(image, screenX, screenY, null);
            this.changeAlphaCompositeValue(g2, 1f);
        }
    }

    private void drawHealthBar(Graphics2D g2, int screenX, int screenY) {
        double healthBarScale = (double) this.gamePanel.tileSize / this.maxHearts;
        double healthBarValue = healthBarScale * this.currentHearts;
        int healthBarWidth = this.gamePanel.tileSize;
        int healthBarHeight = 9;
        int healthBarYPositionAboveEntity = 5;

        g2.setColor(new Color(207, 181, 59));
        g2.fillRect(screenX - 2, screenY - healthBarYPositionAboveEntity - 1, healthBarWidth + 2, healthBarHeight + 2);
        g2.setColor(new Color(30, 30, 30));
        g2.fillRect(screenX, screenY - healthBarYPositionAboveEntity, healthBarWidth - 1, healthBarHeight);
        g2.setColor(new Color(139, 0, 0));
        g2.fillRect(screenX, screenY - healthBarYPositionAboveEntity, (int) healthBarValue, healthBarHeight);
        g2.drawImage(this.gamePanel.gui.healthBarHeart, screenX - 10, screenY - healthBarYPositionAboveEntity - 4, healthBarHeight + 7, healthBarHeight + 7, null);

        this.healthBarCounterFrames++;
        if (this.healthBarCounterFrames > (this.gamePanel.FPS * 10)) {
            this.showHealthBar = false;
            this.healthBarCounterFrames = 0;
        }
    }

    private void drawDyingAnimation(Graphics2D g2) {
        this.dyingCounterFrames++;
        // blink animation for dying entity every 5 Frames change
        if (dyingCounterFrames % 5 == 0) { changeAlphaCompositeValue(g2, 0f); }
        else { changeAlphaCompositeValue(g2, 1f); }
        if (this.dyingCounterFrames > (this.gamePanel.FPS)) {
            this.isAlive = false;
        }
    }

    private void changeAlphaCompositeValue(Graphics2D g2, float alpha) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
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
