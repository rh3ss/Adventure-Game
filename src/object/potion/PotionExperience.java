package object.potion;

import entity.Entity;
import main.GamePanel;

import java.awt.Color;

public class PotionExperience extends Potion {

    public PotionExperience(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.down1 = this.setupEntityImage("/res/objects/potion_green.png", gamePanel.tileSize, gamePanel.tileSize);
        this.color = new Color(0x7cce97);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectBenefitValue = 5;
        this.objectDescription = "[Experience Potion]\nReceive +" + this.objectBenefitValue + " Experience.";
    }

    public void use(Entity entity) {
        String message = "+" + this.objectBenefitValue + " Experience";
        this.gamePanel.gui.addMessage(message, this.color);
        entity.currentExperience += this.objectBenefitValue;
        this.gamePanel.player.checkPlayerLevelUp();
    }
}
