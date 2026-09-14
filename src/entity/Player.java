package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enums.*;
import main.GamePanel;
import main.Keyboard;
import object.ObjectFireBall;
import object.shield.ShieldWood;
import object.weapon.SwordIron;
import object.ObjectWood;
import tileInteractive.InteractiveTile;

public class Player extends Entity {
    private final Keyboard keyboard;
    public int screenX;
    public int screenY;
    public ArrayList<Entity> inventory;
    public int inventoryColumnSize, inventoryRowSize;
    public int maxInventorySize;

    public Player(GamePanel p, Keyboard k) {
        super(p);
        this.keyboard = k;

        this.setDefaultValues();
        this.getMovingImages();
        this.getAttackImages();
        this.setInventory();
    }

    private void setDefaultValues() {
        this.entityType = EntityType.PLAYER;
        // centering player
        this.screenX = (this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize / 2);
        this.screenY = (this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize / 2);
        this.worldX = (this.gamePanel.worldWidth / 2);
        this.worldY = (this.gamePanel.worldHeight / 2);
        // player status
        this.velocity = 4; this.strength = 1; this.dexterity = 1; this.coins = 0;
        this.maxHearts = 5; this.currentHearts = this.maxHearts;
        this.maxMana = 3; this.currentMana = this.maxMana;
        this.currentLevel = 1; this.currentExperience = 0; this.nextLevelExperience = 10;
        this.currentWeapon = new SwordIron(this.gamePanel, -1, -1);
        this.currentShield = new ShieldWood(this.gamePanel, -1, -1);
        this.currentProjectile = new ObjectFireBall(this.gamePanel);
        this.attackDamage = this.getAttackDamage();
        this.defenseArmor = this.getDefenseArmor();
    }

    public double getAttackDamage() {
        this.attackArea = this.currentWeapon.attackArea;
        return this.strength + (this.strength * this.currentWeapon.objectAttackDamageMultiplier);
    }

    private double getDefenseArmor() {
        return this.dexterity + (this.dexterity * this.currentShield.objectDamageReductionMultiplier);
    }

    private void getMovingImages() {
        // MOVEMENT
        this.up1 = this.setupEntityImage("/res/player/moving/player_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/player/moving/player_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/player/moving/player_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/player/moving/player_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/player/moving/player_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/player/moving/player_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/player/moving/player_right_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/player/moving/player_right_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    private void getAttackImages() {
        if (this.currentWeapon.objectType == ObjectType.SWORD) {
            // SWORD
            this.attackUp1 = this.setupEntityImage("/res/player/attack/boy_attack_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackUp2 = this.setupEntityImage("/res/player/attack/boy_attack_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackDown1 = this.setupEntityImage("/res/player/attack/boy_attack_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackDown2 = this.setupEntityImage("/res/player/attack/boy_attack_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackLeft1 = this.setupEntityImage("/res/player/attack/boy_attack_left_1.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
            this.attackLeft2 = this.setupEntityImage("/res/player/attack/boy_attack_left_2.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
            this.attackRight1 = this.setupEntityImage("/res/player/attack/boy_attack_right_1.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
            this.attackRight2 = this.setupEntityImage("/res/player/attack/boy_attack_right_2.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
        }
        else if (this.currentWeapon.objectType == ObjectType.AXE) {
            // AXE
            this.attackUp1 = this.setupEntityImage("/res/player/attack/boy_axe_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackUp2 = this.setupEntityImage("/res/player/attack/boy_axe_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackDown1 = this.setupEntityImage("/res/player/attack/boy_axe_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackDown2 = this.setupEntityImage("/res/player/attack/boy_axe_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize * 2);
            this.attackLeft1 = this.setupEntityImage("/res/player/attack/boy_axe_left_1.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
            this.attackLeft2 = this.setupEntityImage("/res/player/attack/boy_axe_left_2.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
            this.attackRight1 = this.setupEntityImage("/res/player/attack/boy_axe_right_1.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
            this.attackRight2 = this.setupEntityImage("/res/player/attack/boy_axe_right_2.png", this.gamePanel.tileSize * 2, this.gamePanel.tileSize);
        }
    }

    private void setInventory() {
        // inventory
        this.inventory = new ArrayList<>();
        this.inventoryColumnSize = 5;
        this.inventoryRowSize = 4;
        this.maxInventorySize = this.inventoryColumnSize * this.inventoryRowSize;

        this.inventory.add(this.currentWeapon);
        this.inventory.add(this.currentShield);
    }

    public void update() {
        // ATTACKING
        if (this.isAttacking) {
            this.playerIsAttacking();
        }
        // MOVEMENT
        else if (this.keyboard.isUpPressed || this.keyboard.isDownPressed || this.keyboard.isLeftPressed || this.keyboard.isRightPressed || this.keyboard.isEnterPressed) {
            this.playerIsMoving();
        }
        // SHOOTING PROJECTILES
        else if (this.keyboard.isShootingPressed
                && !this.currentProjectile.isAlive
                && this.shootingAvailableCounter == (this.gamePanel.FPS / 2)
                && this.currentProjectile.userCanUseManaByUsageCost(this)) {
            this.playerIsShooting();
        }

        if (isInvincible) {
            this.invincibleCounterFrames++;
            if (this.invincibleCounterFrames > this.gamePanel.FPS) {
                this.isInvincible = false;
                this.invincibleCounterFrames = 0;
            }
        }
        if (this.shootingAvailableCounter < (this.gamePanel.FPS / 2)) {
            this.shootingAvailableCounter++;
        }
        // SET MAX HEARTS
        if (this.currentHearts > this.maxHearts) {
            this.currentHearts = this.maxHearts;
        }
        if (this.currentMana > this.maxMana) {
            this.currentMana = this.maxMana;
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
            this.solidArea.width = this.attackArea.width;
            this.solidArea.height = this.attackArea.height;
            
            int entityIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithEntities(this);
            this.playerAttacksMonster(entityIndex, this.attackDamage);
            this.playerAttacksInteractiveTile(entityIndex, this.attackDamage);

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

        // check collisions
        this.collisionDetected = false;
        this.gamePanel.collisionDetector.detectEntityCollisionWithTile(this);
        int objectIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithObject(this, true);
        this.interactWithCollidedEntity(objectIndex);
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

    private void playerIsShooting() {
        this.currentProjectile.set(this.worldX, this.worldY, this.direction, true, this);
        this.currentProjectile.subtractManaByUsageCost(this);
        this.gamePanel.entities.add(this.currentProjectile);
        this.shootingAvailableCounter = 0;
    }

    private void interactWithCollidedEntity(int entityIndex) {
        if (entityIndex != Integer.MAX_VALUE) {
            Entity entity = this.gamePanel.entities.get(entityIndex);
            if (entity.entityType != null) {
                switch (entity.entityType) {
                    case EntityType.NPC -> { this.playerCollisionWithNPC(entity); }
                    case EntityType.MONSTER -> { this.playerCollisionWithMonster(entity); }
                    case EntityType.OBJECT -> { this.playerCollisionWithObject(entity); }
                }
            }
        }
        else {
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.isAttacking = true;
            }
        }
    }

    private void playerCollisionWithNPC(Entity npc) {
        if (this.gamePanel.keyboard.isEnterPressed) {
            this.gamePanel.gameState = GameState.DIALOGUE;
            npc.speak();
        }
    }

    private void playerCollisionWithMonster(Entity monster) {
        if (!this.isInvincible && this.currentHearts > 0 && !monster.isDying) {
            double monsterDamage = monster.attackDamage - this.defenseArmor;
            if (monsterDamage < 0) {
                monsterDamage = 0;
            }
            if (this.currentHearts - monsterDamage < 0) {
                this.currentHearts = 0;
            }
            else {
                this.currentHearts -= monsterDamage;
            }
            this.isInvincible = true;
        }
    }

    private void playerCollisionWithObject(Entity object) {
        // PICKUP ITEMS
        if (object.objectCategory == ObjectCategory.PICKUP) {
            object.use(this);
            this.gamePanel.entities.remove(object);
        }
        // INVENTORY
        else {
            String collisionMessage;
            if (this.inventory.size() < this.maxInventorySize) {
                collisionMessage = "You found a " + object.objectType.toString();
                this.inventory.add(object);
                this.gamePanel.entities.remove(object);
            } else {
                collisionMessage = "Inventory full!";
            }
            this.gamePanel.gui.addMessage(collisionMessage, Color.WHITE);
        }
    }

    public void playerAttacksMonster(int entityIndex, double attackDamage) {
        if (entityIndex != Integer.MAX_VALUE) {
            Entity monster = this.gamePanel.entities.get(entityIndex);
            if (monster.entityType == EntityType.MONSTER && !monster.isInvincible) {
                double dealtDamage = attackDamage - monster.defenseArmor;
                if (dealtDamage < 0) {
                    dealtDamage = 0;
                }

                this.gamePanel.gui.addMessage("Hit " + (double) Math.round(dealtDamage * 100) + "%", Color.WHITE);
                monster.currentHearts -= dealtDamage;
                monster.receivedDamage = dealtDamage;
                monster.isInvincible = true;
                monster.showReceivedDamage = true;
                monster.damageReaction();
                if (monster.currentHearts < 1) {
                    this.gamePanel.entities.get(entityIndex).isDying = true;
                    this.gamePanel.gui.addMessage("Kill " + monster.monsterType.toString() + "!", Color.WHITE);
                    this.currentExperience += monster.currentExperience;
                    this.gamePanel.gui.addMessage("Exp. +" + monster.currentExperience, Color.WHITE);
                    this.checkPlayerLevelUp();
                }
            }
        }
    }

    private void playerAttacksInteractiveTile(int tileIndex, double attackDamage) {
        if (tileIndex != Integer.MAX_VALUE) {
            if (this.gamePanel.entities.get(tileIndex).entityType != EntityType.INTERACTIVE_TILE) {
                return;
            }
            InteractiveTile tile = (InteractiveTile) this.gamePanel.entities.get(tileIndex);
            if (tile.entityType == EntityType.INTERACTIVE_TILE && !tile.isInvincible && tile.isDestructible && tile.isCorrectObjectEquipped(this)) {
                tile.maxHearts -= 1;
                tile.isInvincible = true;
                this.generateParticle(tile, tile);
                if (tile.maxHearts < 0) {
                    this.gamePanel.entities.set(tileIndex, tile.getFollowingTileAfterDestruction());
                    if (tile.interactiveTileType == InteractiveTileType.DRY_TREE) {
                        ObjectWood droppedWood = new ObjectWood(this.gamePanel, tile.worldX / this.gamePanel.tileSize, tile.worldY / this.gamePanel.tileSize);
                        this.gamePanel.entities.add(droppedWood);
                    }
                }
            }
        }
    }

    private void checkPlayerLevelUp() {
        if (this.currentExperience >= this.nextLevelExperience) {
            this.currentLevel++;
            this.maxHearts++;
            this.strength += 0.1;
            this.dexterity += 0.1;
            this.nextLevelExperience += 10;
            this.attackDamage = this.getAttackDamage();
            this.defenseArmor = this.getDefenseArmor();

            this.gamePanel.gameState = GameState.DIALOGUE;
            this.gamePanel.gui.currentDialogueMessage = "You are level " + this.currentLevel + " now!";
        }
    }

    public void equipCurrentSelectedInventoryItem() {
        int itemIndex = this.gamePanel.gui.getSelectedInventoryItemIndexOnSlot();
        if (itemIndex < this.inventory.size()) {
            Entity selectedItem = this.inventory.get(itemIndex);
            if (selectedItem.entityType == EntityType.OBJECT) {
                switch (selectedItem.objectCategory) {
                    case ObjectCategory.WEAPON:
                        this.currentWeapon = selectedItem;
                        this.attackDamage = this.getAttackDamage();
                        this.getAttackImages();
                        break;
                    case ObjectCategory.SHIELD:
                        this.currentShield = selectedItem;
                        this.defenseArmor = this.getDefenseArmor();
                        break;
                    case ObjectCategory.CONSUMABLE:
                        selectedItem.use(this);
                        this.inventory.remove(itemIndex);
                        break;
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
