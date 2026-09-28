package entity;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import enums.*;
import main.GamePanel;
import main.Keyboard;
import monster.Monster;
import npc.NPC;
import object.GameObject;
import object.armor.Armor;
import object.shield.Shield;
import object.weapon.Weapon;
import projectile.Fireball;
import object.armor.ArmorIron;
import object.shield.ShieldWood;
import object.weapon.SwordIron;
import object.environment.Wood;
import projectile.Projectile;
import tileInteractive.InteractiveTile;

public class Player extends Entity {
    private final Keyboard keyboard;
    public int screenX;
    public int screenY;

    public Player(GamePanel p, Keyboard k) {
        super(p);
        this.keyboard = k;

        this.setDefaultValues();
        this.getMovingImages();
        this.getAttackImages();
        this.setInventory();
    }

    public void setDefaultValues() {
        this.entityType = EntityType.PLAYER;
        // centering player
        this.screenX = (this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize / 2);
        this.screenY = (this.gamePanel.screenHeight / 2) - (this.gamePanel.tileSize / 2);
        this.worldX = (this.gamePanel.tileSize * 22);
        this.worldY = (this.gamePanel.tileSize * 24);
        // player status
        this.defaultVelocity = 4; this.velocity = this.defaultVelocity;
        this.strength = 1; this.dexterity = 1; this.coins = 100;
        this.maxHearts = 5; this.currentHearts = this.maxHearts;
        this.maxMana = 3; this.currentMana = this.maxMana;
        this.currentLevel = 1; this.currentExperience = 0; this.nextLevelExperience = 10;
        this.currentWeapon = new SwordIron(this.gamePanel, -1, -1);
        this.currentShield = new ShieldWood(this.gamePanel, -1, -1);
        this.currentArmor = new ArmorIron(this.gamePanel, -1, -1);
        this.currentProjectile = new Fireball(this.gamePanel);
        this.attackDamage = this.getAttackDamage();
        this.defenseArmor = this.getDefenseArmor();
    }
    public void setDefaultValuesAfterRespawn() {
        this.worldX = (this.gamePanel.tileSize * 22);
        this.worldY = (this.gamePanel.tileSize * 24);
        this.maxHearts = 5; this.currentHearts = this.maxHearts;
        this.maxMana = 3; this.currentMana = this.maxMana;
        this.isInvincible = false;
    }

    private void setInventory() {
        // inventory
        this.inventory = new ArrayList<>();
        this.inventoryColumnSize = 5;
        this.inventoryRowSize = 4;
        this.maxInventorySize = this.inventoryColumnSize * this.inventoryRowSize;

        this.inventory.add(this.currentWeapon);
        this.inventory.add(this.currentShield);
        this.inventory.add(this.currentArmor);
    }
    public void setInventoryObjects() {
        this.inventory.clear();
        this.inventory.add(this.currentWeapon);
        this.inventory.add(this.currentShield);
        this.inventory.add(this.currentArmor);
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
        this.attackUp1 = this.setupEntityImage("/res/player/moving/player_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackUp2 = this.setupEntityImage("/res/player/moving/player_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackDown1 = this.setupEntityImage("/res/player/moving/player_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackDown2 = this.setupEntityImage("/res/player/moving/player_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackLeft1 = this.setupEntityImage("/res/player/moving/player_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackLeft2 = this.setupEntityImage("/res/player/moving/player_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackRight1 = this.setupEntityImage("/res/player/moving/player_right_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackRight2 = this.setupEntityImage("/res/player/moving/player_right_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }
    private double getAttackProgress() {
        double chargeFrames = this.gamePanel.FPS / 10.0;
        double attackFrames = this.gamePanel.FPS / 2.0;
        double progress = (this.animationCounterFrames - chargeFrames) / (attackFrames - chargeFrames);
        return Math.pow(progress, 0.5);
    }
    private double getWeaponSwingAngle() {
        double directionAngle = 0;
        switch (this.direction) {
            case Direction.UP -> { directionAngle = -45; }
            case Direction.RIGHT -> { directionAngle = 45; }
            case Direction.DOWN -> { directionAngle = 135; }
            case Direction.LEFT -> { directionAngle = -135; }
        };

        double attackProgress = this.getAttackProgress();
        double attackAngle = this.currentWeapon.attackStartAngle + (this.currentWeapon.attackEndAngle - this.currentWeapon.attackStartAngle) * attackProgress;
        if (this.direction == Direction.RIGHT || this.direction == Direction.DOWN) {
            attackAngle *= -1;
        }
        return directionAngle;
        // later
        // return directionAngle + attackAngle;
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
                && this.shootingAvailableCounterFrames == (this.gamePanel.FPS / 2)
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
        if (this.shootingAvailableCounterFrames < (this.gamePanel.FPS / 2)) {
            this.shootingAvailableCounterFrames++;
        }
        // SET MAX HEARTS
        if (this.currentHearts > this.maxHearts) {
            this.currentHearts = this.maxHearts;
        }
        if (this.currentMana > this.maxMana) {
            this.currentMana = this.maxMana;
        }

        if (this.currentHearts <= 0) {
            this.gamePanel.gameState = GameState.GAME_OVER;
            this.gamePanel.gui.gameOverSelection = GameOverSelection.NEGATIVE;
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
            this.playerAttacksMonster(entityIndex, this.attackDamage, this.currentWeapon.knockBackPower);
            this.playerAttacksInteractiveTile(entityIndex);
            this.playerAttacksProjectile(entityIndex);

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
        this.collisionDetected = false;
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
        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(this.currentProjectile);
        this.shootingAvailableCounterFrames = 0;
    }

    private void interactWithCollidedEntity(int entityIndex) {
        if (entityIndex != Integer.MAX_VALUE) {
            Entity entity = this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(entityIndex);
            if (entity.entityType != null) {
                switch (entity.entityType) {
                    case EntityType.NPC -> { this.playerCollisionWithNPC( (NPC) entity); }
                    case EntityType.MONSTER -> { this.playerCollisionWithMonster( (Monster) entity); }
                    case EntityType.OBJECT -> { this.playerCollisionWithObject( (GameObject) entity); }
                }
            }
        }
        else {
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.isAttacking = true;
            }
        }
    }
    private void playerCollisionWithNPC(NPC npc) {
        if (this.gamePanel.keyboard.isEnterPressed) {
            this.gamePanel.gameState = GameState.DIALOGUE;
            this.isAttacking = false;
            npc.speak();
        }
    }
    private void playerCollisionWithMonster(Monster monster) {
        if (!this.isInvincible && this.currentHearts > 0 && !monster.isDying) {
            double monsterDamage = monster.attackDamage - this.defenseArmor;
            if (monsterDamage < 0) {
                monsterDamage = 0;
            }
            if (this.currentHearts - monsterDamage < 0) { this.currentHearts = 0; }
            else { this.currentHearts -= monsterDamage; }
            this.isInvincible = true;
        }
    }
    private void playerCollisionWithObject(GameObject object) {
        // PICKUP ITEMS
        if (object.objectCategory == ObjectCategory.PICKUP) {
            object.use(this);
            this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.remove(object);
        }
        // OBSTACLE
        else if (object.objectCategory == ObjectCategory.OBSTACLE) {
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.isAttacking = false;
                object.interact();
            }
        }
        // INVENTORY
        else {
            String collisionMessage;
            if (this.playerCanObtainObjectInInventory(object)) {
                collisionMessage = "You found a " + object.objectType.toString();
                // this.inventory.add(object);
                this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.remove(object);
            } else {
                collisionMessage = "Inventory full!";
            }
            this.gamePanel.gui.inventoryFull = (this.inventory.size() == this.maxInventorySize);
            this.gamePanel.gui.addMessage(collisionMessage, Color.WHITE);
        }
    }

    private void playerKnockBackEntity(Entity entity, int knockBackPower) {
        entity.direction = this.direction;
        entity.velocity += knockBackPower;
        entity.receivedKnockBack = true;
    }
    public void playerAttacksMonster(int entityIndex, double attackDamage, int knockBackPower) {
        if (entityIndex != Integer.MAX_VALUE) {
            if (!(this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(entityIndex) instanceof Monster monster)) {
                return;
            }
            if (!monster.isInvincible) {
                if (knockBackPower > 0) {
                    this.playerKnockBackEntity(monster, knockBackPower);
                }
                double dealtDamage = attackDamage - monster.defenseArmor;
                if (dealtDamage < 0) {
                    dealtDamage = 0;
                }

                monster.currentHearts -= dealtDamage;
                monster.isInvincible = true;
                monster.damageReaction();
                if (monster.currentHearts < 1) {
                    this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(entityIndex).isDying = true;
                    this.currentExperience += monster.currentExperience;
                    this.gamePanel.gui.addMessage("Exp. +" + monster.currentExperience, Color.WHITE);
                    this.checkPlayerLevelUp();
                }
            }
        }
    }
    private void playerAttacksInteractiveTile(int tileIndex) {
        if (tileIndex != Integer.MAX_VALUE) {
            if (!(this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(tileIndex) instanceof InteractiveTile tile)) {
                return;
            }
            if (!tile.isInvincible && tile.isDestructible && tile.isCorrectObjectEquipped(this)) {
                tile.maxHearts -= 1;
                tile.isInvincible = true;
                this.generateParticle(tile, tile);
                if (tile.maxHearts < 0) {
                    this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.set(tileIndex, tile.getFollowingTileAfterDestruction());
                    if (tile.interactiveTileType == InteractiveTileType.DRY_TREE) {
                        Wood droppedWood = new Wood(this.gamePanel, tile.worldX / this.gamePanel.tileSize, tile.worldY / this.gamePanel.tileSize);
                        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(droppedWood);
                    }
                    else if (tile.interactiveTileType == InteractiveTileType.BIG_TREE) {
                        Wood droppedWood = new Wood(this.gamePanel, tile.worldX / this.gamePanel.tileSize, (tile.worldY / this.gamePanel.tileSize) + 1);
                        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(droppedWood);
                    }
                }
            }
        }
    }
    private void playerAttacksProjectile(int projectileIndex) {
        if (projectileIndex != Integer.MAX_VALUE) {
            if (!(this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(projectileIndex) instanceof Projectile projectile)) {
                return;
            }
            projectile.isAlive = false;
            this.generateParticle(projectile, projectile);
        }
    }

    public void checkPlayerLevelUp() {
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
    public void equipCurrentSelectedInventoryObject() {
        int itemIndex = this.gamePanel.gui.getSelectedInventoryItemIndexOnSlot(this.gamePanel.gui.playerInventorySlotColumnSelected, this.gamePanel.gui.playerInventorySlotRowSelected);
        if (itemIndex < this.inventory.size() && this.inventory.get(itemIndex) instanceof GameObject selectedObject) {
            switch (selectedObject.objectCategory) {
                case ObjectCategory.WEAPON:
                    this.currentWeapon = (Weapon) selectedObject;
                    this.attackDamage = this.getAttackDamage();
                    this.getAttackImages();
                    break;
                case ObjectCategory.SHIELD:
                    this.currentShield = (Shield) selectedObject;
                    this.defenseArmor = this.getDefenseArmor();
                    break;
                case ObjectCategory.ARMOR:
                    this.currentArmor = (Armor) selectedObject;
                    break;
                case ObjectCategory.CONSUMABLE:
                    selectedObject.use(this);
                    if (selectedObject.objectCurrentStackableAmount > 1) {
                        selectedObject.objectCurrentStackableAmount--;
                    }
                    else {
                        this.inventory.remove(itemIndex);
                    }
                    break;
                case ObjectCategory.INTERACTABLE:
                    selectedObject.use(this);
                    break;
            }
        }
    }
    public int searchObjectInInventory(ObjectType objectType) {
        int objectIndex = Integer.MAX_VALUE;
        for (int idx = 0; idx < this.inventory.size(); ++idx) {
            if (this.inventory.get(idx).objectType == objectType) {
                objectIndex = idx;
                break;
            }
        }
        return objectIndex;
    }
    public boolean playerCanObtainObjectInInventory(GameObject object) {
        boolean canObtain = false;
        if (object.isStackable) {
            int objectIndex = this.searchObjectInInventory(object.objectType);
            // add stackable
            if (objectIndex != Integer.MAX_VALUE) {
                this.inventory.get(objectIndex).objectCurrentStackableAmount++;
                canObtain = true;
            }
            // new object
            else {
                if (this.inventory.size() != this.maxInventorySize) {
                    this.inventory.add(object);
                    canObtain = true;
                }
            }
        }
        // not stackable
        else {
            if (this.inventory.size() != this.maxInventorySize) {
                this.inventory.add(object);
                canObtain = true;
            }
        }
        return canObtain;
    }

    public void draw(Graphics2D g2) {
        if (this.isInvincible) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if (this.isAttacking && this.animationFrame == 2) {
            this.drawCurrentWeapon(g2);
        }
        BufferedImage playerImage = this.getPlayersAnimationFrameImage();
        g2.drawImage(playerImage, this.screenX, this.screenY, null);
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
    private void drawCurrentWeapon(Graphics2D g2) {
        BufferedImage weaponImage = this.currentWeapon.down1;
        double weaponSwingAngle = this.getWeaponSwingAngle();
        double playersHandX = this.screenX + this.gamePanel.tileSize / 2.0;
        double playersHandY = this.screenY + this.gamePanel.tileSize / 2.0;
        if (this.direction == Direction.UP || this.direction == Direction.LEFT || this.direction == Direction.RIGHT) {
            playersHandY += 15;
        }
        // transformation of Graphics2D for rotation
        AffineTransform oldTransform = g2.getTransform();
        AffineTransform weaponTransform = new AffineTransform();
        weaponTransform.translate(playersHandX, playersHandY);
        if (this.direction == Direction.RIGHT) {
            // rotate image 90° then mirror it and rotate back
            weaponTransform.rotate(Math.toRadians(90));
            weaponTransform.scale(-1, 1);
            weaponTransform.rotate(Math.toRadians(-90));
        }
        weaponTransform.rotate(Math.toRadians(weaponSwingAngle));
        weaponTransform.translate(-0, -weaponImage.getHeight());
        // draw rotated image and reset transformation
        g2.drawImage(weaponImage, weaponTransform, null);
        g2.setTransform(oldTransform);
    }
}
