package monster;

import entity.Entity;
import enums.Direction;
import enums.EntityType;
import enums.MonsterType;
import main.GamePanel;
import object.ObjectBullet;

import java.awt.Rectangle;
import java.util.Random;

public class GreenSlime extends Entity {

    public GreenSlime(GamePanel p, int worldColumn, int worldRow) {
        super(p);

        this.entityType = EntityType.MONSTER;
        this.monsterType = MonsterType.GREEN_SLIME;
        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;
        this.velocity = 1;
        this.maxHearts = 4;
        this.currentHearts = this.maxHearts;
        this.currentExperience = 3;
        this.attackDamage = 3;
        this.defenseArmor = 0;

        this.isSolid = true;
        this.solidArea = new Rectangle(3, 18, 42, 30);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        this.currentProjectile = new ObjectBullet(p);
        this.getImages();
    }

    private void getImages() {
        this.up1 = this.setupEntityImage("/res/monster/ball_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/monster/ball_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/monster/ball_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/monster/ball_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/monster/ball_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/monster/ball_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/monster/ball_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/monster/ball_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public void setAction() {
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

        int randomNumber = new Random().nextInt(101);
        if (randomNumber > 99 && !this.currentProjectile.isAlive && this.shootingAvailableCounter == (this.gamePanel.FPS / 2)) {
            this.currentProjectile.set(this.worldX, this.worldY, this.direction, true, this);
            this.gamePanel.entities.add(this.currentProjectile);
            this.shootingAvailableCounter = 0;
        }
    }

    public void damageReaction() {
        this.actionCounterFrames = 0;
        this.direction = this.gamePanel.player.direction;
    }
}
