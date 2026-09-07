package object.potion;

import entity.Entity;
import main.GamePanel;

import java.awt.Color;

public class PotionSpeed extends Potion {

    public PotionSpeed(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/objects/potion_yellow.png", gamePanel.tileSize, gamePanel.tileSize);
        this.color = new Color(0xffee8c);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectBenefitValue = 1;
        this.objectDescription = "[Speed Potion]\nReceive +" + this.objectBenefitValue * 10 + "% Velocity.";
    }

    public void use(Entity entity) {
        String message = "+" + this.objectBenefitValue * 10 + "% Velocity";
        this.gamePanel.gui.addMessage(message, this.color);
        entity.velocity += this.objectBenefitValue / 10;
    }
}
