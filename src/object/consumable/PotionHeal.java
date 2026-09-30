package object.consumable;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class PotionHeal extends Consumable {

    public PotionHeal(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.POTION_HEAL;
        this.down1 = this.setupEntityImage("/res/objects/potion_red.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectColor = new Color(0x800517);
        this.objectBenefitValue = 2;
        this.objectCoinValue = 5;
        this.objectName = "Heal Potion";
        this.objectDescription = "[" + this.objectName + "]\nHeals +" + this.objectBenefitValue + " Hearts.";
    }

    public void use(Entity entity) {
        String message = "+" + this.objectBenefitValue + " Hearts";
        this.gamePanel.gui.addMessage(message, this.objectColor);
        entity.currentHearts += this.objectBenefitValue;
    }
}
