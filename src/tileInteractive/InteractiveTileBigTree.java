package tileInteractive;

import entity.Entity;
import enums.InteractiveTileType;
import enums.ObjectType;
import main.GamePanel;

import java.awt.*;

public class InteractiveTileBigTree extends InteractiveTile {

    public InteractiveTileBigTree(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.BIG_TREE;
        this.isSolid = true;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/big_tree.png", gamePanel.tileSize, gamePanel.tileSize * 2);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = (worldRow * gamePanel.tileSize) - gamePanel.tileSize;

        int entityCollisionOffset = 8;
        this.solidArea = new Rectangle(
                entityCollisionOffset,
                entityCollisionOffset * 2,
                gamePanel.tileSize - (entityCollisionOffset * 2),
                (gamePanel.tileSize * 2) - (entityCollisionOffset * 2)
        );
        this.solidAreaDefaultX = entityCollisionOffset;
        this.solidAreaDefaultY = entityCollisionOffset * 6;
    }

    public boolean isCorrectObjectEquipped(Entity user) {
        return (user.currentWeapon.objectType == ObjectType.AXE);
    }

    public InteractiveTile getFollowingTileAfterDestruction() {
        return new InteractiveTileTrunk(this.gamePanel, this.worldX / this.gamePanel.tileSize, (this.worldY / this.gamePanel.tileSize) + 1);
    }

    public Color getParticleColor() { return new Color(0x4A2511); }

    public int getParticlePxSize() { return 6; }

    public int getParticleVelocity() { return 1; }

    public int getParticleMaxHearts() { return (this.gamePanel.FPS / 4); }
}
