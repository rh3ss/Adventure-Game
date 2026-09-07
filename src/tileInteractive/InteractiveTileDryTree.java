package tileInteractive;

import entity.Entity;
import enums.InteractiveTileType;
import enums.ObjectType;
import main.GamePanel;

import java.awt.*;

public class InteractiveTileDryTree extends InteractiveTile {

    public InteractiveTileDryTree(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.DRY_TREE;
        this.isDestructible = true;
        this.isSolid = true;
        this.maxHearts = 3;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/drytree.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }

    public boolean isCorrectObjectEquipped(Entity user) {
        return (user.currentWeapon.objectType == ObjectType.AXE);
    }

    public InteractiveTile getFollowingTileAfterDestruction() {
        return new InteractiveTileTrunk(this.gamePanel, this.worldX / this.gamePanel.tileSize, this.worldY / this.gamePanel.tileSize);
    }

    public Color getParticleColor() { return new Color(0x4A2511); }

    public int getParticlePxSize() { return 6; }

    public int getParticleVelocity() { return 1; }

    public int getParticleMaxHearts() { return (this.gamePanel.FPS / 4); }
}
