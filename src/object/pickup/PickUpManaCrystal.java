package object.pickup;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class PickUpManaCrystal extends PickUp {

    public PickUpManaCrystal(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.MANA_CRYSTAL;
        this.down1 = this.setupEntityImage("/res/objects/manacrystal_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image1 = this.setupEntityImage("/res/objects/manacrystal_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image2 = this.setupEntityImage("/res/objects/manacrystal_blank.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectColor = Color.WHITE;
        this.objectBenefitValue = 1;
        this.objectName = "Mana Crystal";
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectType.toString() + " +" + this.objectBenefitValue, this.objectColor);
        entity.currentMana += this.objectBenefitValue;
    }
}
