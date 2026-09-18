package projectile;

import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class Bullet extends Projectile {
    private final GamePanel gamePanel;

    public Bullet(GamePanel p) {
        super(p);
        this.gamePanel = p;

        this.objectType = ObjectType.BULLET;
        this.velocity = 9;
        this.attackDamage = 2;
        this.getImages();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/projectile/bullet_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public Color getParticleColor() { return new Color(0x2A2F35); }
}
