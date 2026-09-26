package projectile;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class Fireball extends Projectile {
    private final GamePanel gamePanel;

    public Fireball(GamePanel p) {
        super(p);
        this.gamePanel = p;

        this.objectType = ObjectType.FIREBALL;
        this.velocity = 7;
        this.attackDamage = 2;
        this.projectileUsageCostValue = 1;
        this.knockBackPower = 0;
        this.getImages();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/projectile/fireball_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/projectile/fireball_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/projectile/fireball_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/projectile/fireball_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/projectile/fireball_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/projectile/fireball_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/projectile/fireball_right_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/projectile/fireball_right_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public boolean userCanUseManaByUsageCost(Entity user) {
        return (user.currentMana >= this.projectileUsageCostValue);
    }

    public void subtractManaByUsageCost(Entity user) {
        user.currentMana -= this.projectileUsageCostValue;
    }

    public Color getParticleColor() { return new Color(0x780606); }
}
