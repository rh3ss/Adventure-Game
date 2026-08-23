package monster;

import entity.Entity;
import enums.Direction;
import enums.EntityType;
import enums.MonsterType;
import main.GamePanel;

import java.awt.*;
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
    }

    public void damageReaction() {
        this.actionCounterFrames = 0;
        this.direction = this.gamePanel.player.direction;
    }
}
