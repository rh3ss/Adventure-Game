package object.potion;

import entity.Entity;
import main.GamePanel;

import java.awt.Color;

public class PotionSpeed extends Potion {

    public PotionSpeed(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/objects/potion_yellow.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectColor = new Color(0xffee8c);
        this.objectBenefitValue = 1;
        this.objectCoinPrice = 20;
        this.objectDescription = "[Speed Potion]\nReceive +" + this.objectBenefitValue * 10 + "% Velocity.";
    }

    public void use(Entity entity) {
        String message = "+" + this.objectBenefitValue * 10 + "% Velocity";
        this.gamePanel.gui.addMessage(message, this.objectColor);
        entity.velocity += this.objectBenefitValue / 10;
    }
}
