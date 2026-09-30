package tileInteractive;

import entity.Entity;
import enums.InteractiveTileType;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class SmallTree extends InteractiveTile {

    public SmallTree(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.SMALL_TREE;
        this.isDestructible = true;
        this.isSolid = true;
        this.maxHearts = 3;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/tree_small.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }

    public boolean isCorrectObjectEquipped(Entity user) {
        return (user.currentWeapon.objectType == ObjectType.AXE);
    }

    public InteractiveTile getFollowingTileAfterDestruction() {
        return new Trunk(this.gamePanel, this.worldX / this.gamePanel.tileSize, this.worldY / this.gamePanel.tileSize);
    }

    public Color getParticleColor() { return new Color(0x4A2511); }
}
