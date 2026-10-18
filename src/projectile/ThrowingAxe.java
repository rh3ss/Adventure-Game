package projectile;

import enums.Direction;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class ThrowingAxe extends Projectile {
    private final GamePanel gamePanel;

    public ThrowingAxe(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        this.objectType = ObjectType.AXE;
        this.velocity = 5;
        this.attackDamage = 2;
        this.knockBackPower = 0;
    }

    public void getImages() {
        this.up1 = this.up2 = this.down1 = this.down2 = this.left1 = this.left2 = this.right1 = this.right2 = this.gamePanel.player.currentWeapon.down1;
    }

    public void update() {
        super.update();

        // after half hearts axe returns to player
        if (this.currentHearts == (this.maxHearts / 2)) {
            switch (this.direction) {
                case Direction.UP -> { this.direction = Direction.DOWN; }
                case Direction.DOWN -> { this.direction = Direction.UP; }
                case Direction.LEFT -> { this.direction = Direction.RIGHT; }
                case Direction.RIGHT -> { this.direction = Direction.LEFT; }
            }
        }
    }

    public Color getParticleColor() { return new Color(0xc4c4c4); }
}
