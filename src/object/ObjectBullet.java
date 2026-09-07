package object;

import entity.Entity;
import entity.Projectile;
import enums.EntityType;
import enums.ObjectType;
import main.GamePanel;

import java.awt.*;

public class ObjectBullet extends Projectile {
    private final GamePanel gamePanel;

    public ObjectBullet(GamePanel p) {
        super(p);
        this.gamePanel = p;

        this.entityType = EntityType.PROJECTILE;
        this.objectType = ObjectType.BULLET;
        this.velocity = 9;
        this.maxHearts = 100;
        this.currentHearts = this.maxHearts;
        this.attackDamage = 2;
        this.isAlive = false;
        this.getImages();
    }

    private void getImages() {
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

    public int getParticlePxSize() { return 10; }

    public int getParticleVelocity() { return 1; }

    public int getParticleMaxHearts() { return (this.gamePanel.FPS / 4); }
}
