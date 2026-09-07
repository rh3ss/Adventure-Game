package object.potion;

import entity.Entity;
import main.GamePanel;

import java.awt.Color;

public class PotionStrength extends Potion {

    public PotionStrength(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/objects/potion_blue.png", gamePanel.tileSize, gamePanel.tileSize);
        this.color = new Color(0x040273);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectBenefitValue = 1;
        this.objectDescription = "[Blue Potion]\nReceive +" + this.objectBenefitValue * 10 + "% Strength.";
    }

    public void use(Entity entity) {
        String message = "+" + this.objectBenefitValue * 10 + " % Strength";
        this.gamePanel.gui.addMessage(message, this.color);
        entity.strength += (double) this.objectBenefitValue / 10;
    }
}
