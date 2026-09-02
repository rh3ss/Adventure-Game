package object;

import entity.Projectile;
import enums.ObjectType;
import main.GamePanel;

public class ObjectFireBall extends Projectile {
    private GamePanel gamePanel;

    public ObjectFireBall(GamePanel p) {
        super(p);
        this.gamePanel = p;

        this.objectType = ObjectType.FIREBALL;
        this.velocity = 7;
        this.maxHearts = 100;
        this.currentHearts = this.maxHearts;
        this.attackDamage = 2;
        this.objectUsageCostValue = 1;
        this.isAlive = false;
        this.getImages();
    }

    private void getImages() {
        this.up1 = this.setupEntityImage("/res/projectile/fireball_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/projectile/fireball_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/projectile/fireball_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/projectile/fireball_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/projectile/fireball_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/projectile/fireball_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/projectile/fireball_right_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/projectile/fireball_right_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }
}
