package tileInteractive;

import entity.Entity;
import enums.InteractiveTileType;
import main.GamePanel;

import java.awt.Color;

public class InteractiveTileWheat extends InteractiveTile {

    public InteractiveTileWheat(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.WHEAT;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/wheat.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }

    public boolean isCorrectObjectEquipped(Entity user) {
        return true;
    }

    public Color getParticleColor() { return new Color(0xad8b30); }

    public int getParticlePxSize() { return 6; }

    public int getParticleVelocity() { return 1; }

    public int getParticleMaxHearts() { return (this.gamePanel.FPS / 4); }
}
