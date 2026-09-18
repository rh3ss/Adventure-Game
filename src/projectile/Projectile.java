package projectile;

import entity.Entity;
import enums.Direction;
import enums.EntityType;
import main.GamePanel;

public class Projectile extends Entity {
    private Entity userOfProjectile;

    public Projectile(GamePanel p) {
        super(p);

        this.entityType = EntityType.PROJECTILE;

    }

    public void set(int worldX, int worldY, Direction direction, boolean isAlive, Entity user) {
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.isAlive = isAlive;
        this.userOfProjectile = user;
        this.currentHearts = this.maxHearts;
    }

    public void update() {
        if (this.userOfProjectile == this.gamePanel.player) {
            int entityIndex = this.gamePanel.collisionDetector.detectEntityCollisionWithEntities(this);
            if (entityIndex != Integer.MAX_VALUE && (this.gamePanel.entities.get(entityIndex) != this.gamePanel.player)) {
                this.gamePanel.player.playerAttacksMonster(entityIndex, this.attackDamage);
                this.generateParticle(this.userOfProjectile.currentProjectile, this.gamePanel.entities.get(entityIndex));
                this.isAlive = false;
            }
        }
        else {
            boolean collisionWithPlayer = this.gamePanel.collisionDetector.detectEntityCollisionWithPlayer(this);
            if (!this.gamePanel.player.isInvincible && collisionWithPlayer) {
                this.entityDamagePlayer(this.attackDamage);
                this.generateParticle(this.userOfProjectile.currentProjectile, this.gamePanel.player);
                this.isAlive = false;
            }
        }

        switch (this.direction) {
            case Direction.UP -> { this.worldY -= this.velocity; }
            case Direction.DOWN -> { this.worldY += this.velocity; }
            case Direction.LEFT -> { this.worldX -= this.velocity; }
            case Direction.RIGHT -> { this.worldX += this.velocity; }
        }

        // remove hearts from projectile so it does not exist forever
        this.currentHearts--;
        if (this.currentHearts <= 0) {
            this.isAlive = false;
        }

        this.animationCounterFrames++;
        if (this.animationCounterFrames >= 12) {
            this.animationFrame = (this.animationFrame + 1) % 3;
        }
    }

    public boolean userCanUseManaByUsageCost(Entity user) {
        return false;
    }

    public void subtractManaByUsageCost(Entity user) { }
}
