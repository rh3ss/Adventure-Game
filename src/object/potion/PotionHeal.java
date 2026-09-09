package object.potion;

import entity.Entity;
import main.GamePanel;

import java.awt.Color;

public class PotionHeal extends Potion {

    public PotionHeal(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/objects/potion_red.png", gamePanel.tileSize, gamePanel.tileSize);
        this.color = new Color(0x800517);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectBenefitValue = 2;
        this.objectDescription = "[Heal Potion]\nHeals +" + this.objectBenefitValue + " Hearts.";
    }

    public void use(Entity entity) {
        String message = "+" + this.objectBenefitValue + " Hearts";
        this.gamePanel.gui.addMessage(message, this.color);
        entity.currentHearts += this.objectBenefitValue;
    }
}
