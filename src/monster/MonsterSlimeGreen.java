package monster;

import entity.Entity;
import enums.MonsterType;
import main.GamePanel;
import object.pickup.PickUpCoin;
import object.pickup.PickUpHeart;
import object.pickup.PickUpManaCrystal;

import java.awt.Rectangle;

public class MonsterSlimeGreen extends Monster {

    public MonsterSlimeGreen(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.monsterType = MonsterType.SLIME_GREEN;
        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;
        this.velocity = 1;
        this.maxHearts = 6;
        this.currentHearts = this.maxHearts;
        this.currentExperience = 3;
        this.attackDamage = 3;
        this.defenseArmor = 0;

        this.solidArea = new Rectangle(3, 18, 42, 30);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        this.getImages();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/monster/greenslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/monster/greenslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/monster/greenslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/monster/greenslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/monster/greenslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/monster/greenslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/monster/greenslime_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/monster/greenslime_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public void chooseObjectToDrop() {
        int number = this.random.nextInt(101);
        Entity objectToDrop;
        if (number < 50) {
            objectToDrop = new PickUpCoin(this.gamePanel, -1, -1);
        }
        else if (number < 75) {
            objectToDrop = new PickUpHeart(this.gamePanel, -1, -1);
        }
        else {
            objectToDrop = new PickUpManaCrystal(this.gamePanel, -1, -1);
        }
        this.dropObject(objectToDrop);
    }
}
