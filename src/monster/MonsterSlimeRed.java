package monster;

import entity.Entity;
import enums.Direction;
import enums.MonsterType;
import main.GamePanel;
import projectile.Bullet;
import object.pickup.ManaCrystal;
import object.consumable.PotionExperience;
import object.consumable.PotionHeal;

import java.awt.Rectangle;
import java.util.Random;

public class MonsterSlimeRed extends Monster {

    public MonsterSlimeRed(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.monsterType = MonsterType.SLIME_RED;
        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;

        this.defaultVelocity = 2; this.velocity = this.defaultVelocity;
        this.maxHearts = 8; this.currentHearts = this.maxHearts;
        this.currentExperience = 6;
        this.attackDamage = 3; this.defenseArmor = 0;

        this.solidArea = new Rectangle(3, 18, 42, 30);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        this.currentProjectile = new Bullet(gamePanel);
        this.getImages();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/monster/redslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/monster/redslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/monster/redslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/monster/redslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/monster/redslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/monster/redslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/monster/redslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/monster/redslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public void setAction() {
        if (this.onTrackingPath) {
            int destinationColumn = (this.gamePanel.player.worldX + this.gamePanel.player.solidArea.x) / this.gamePanel.tileSize;
            int destinationRow = (this.gamePanel.player.worldY + this.gamePanel.player.solidArea.y) / this.gamePanel.tileSize;
            this.searchDestinationPath(destinationColumn, destinationRow);
            // shooting
            int randomNumber = this.random.nextInt(101);
            if (randomNumber > 99 && !this.currentProjectile.isAlive && this.shootingAvailableCounterFrames == (this.gamePanel.FPS / 2)) {
                this.currentProjectile.set(this.worldX, this.worldY, this.direction, true, this);
                this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(this.currentProjectile);
                this.shootingAvailableCounterFrames = 0;
            }
        }
        else {
            this.actionCounterFrames++;
            if (this.actionCounterFrames > 120) {
                Random r = new Random();
                double number  = r.nextDouble();

                if (number <= 0.25) { this.direction = Direction.UP; }
                else if (number <= 0.50) { this.direction = Direction.DOWN; }
                else if (number <= 0.75) { this.direction = Direction.LEFT; }
                else { this.direction = Direction.RIGHT; }
                this.actionCounterFrames = 0;
            }
        }
    }

    public void chooseObjectToDrop() {
        int number = this.random.nextInt(101);
        Entity objectToDrop;
        if (number < 50) {
            objectToDrop = new PotionExperience(this.gamePanel, -1, -1);
        }
        else if (number < 75) {
            objectToDrop = new PotionHeal(this.gamePanel, -1, -1);
        }
        else {
            objectToDrop = new ManaCrystal(this.gamePanel, -1, -1);
        }
        this.dropObject(objectToDrop);
    }
}
