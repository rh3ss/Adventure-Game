package tileInteractive;

import entity.Entity;
import enums.InteractiveTileType;
import main.GamePanel;

import java.awt.Color;

public class InteractiveTileBush extends InteractiveTile {

    public InteractiveTileBush(GamePanel gamePanel, String bushName, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.interactiveTileType = InteractiveTileType.BUSH;
        this.isDestructible = true;
        this.maxHearts = 1;
        this.down1 = this.setupEntityImage("/res/tilesInteractive/" + bushName + ".png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }

    public boolean isCorrectObjectEquipped(Entity user) {
        return true;
    }

    public Color getParticleColor() { return new Color(0x183029); }

    public int getParticlePxSize() { return 6; }

    public int getParticleVelocity() { return 1; }

    public int getParticleMaxHearts() { return (this.gamePanel.FPS / 4); }
}
