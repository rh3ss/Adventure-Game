package object.potion;

import entity.Entity;
import main.GamePanel;

import java.awt.Color;

public class PotionStrength extends Potion {

    public PotionStrength(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/objects/potion_blue.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectColor = new Color(0x040273);
        this.objectBenefitValue = 1;
        this.objectCoinValue = 10;
        this.objectName = "Strength Potion";
        this.objectDescription = "[" + this.objectName + "]\nReceive +" + this.objectBenefitValue * 10 + "% Strength.";
    }

    public void use(Entity entity) {
        String message = "+" + this.objectBenefitValue * 10 + " % Strength";
        this.gamePanel.gui.addMessage(message, this.objectColor);
        entity.strength += (double) this.objectBenefitValue / 10;
    }
}
