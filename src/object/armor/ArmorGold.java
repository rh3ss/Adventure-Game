package object.armor;

import enums.ObjectType;
import main.GamePanel;

public class ArmorGold extends Armor {

    public ArmorGold(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.ARMOR;
        this.down1 = this.setupEntityImage("/res/objects/armor_gold.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectDamageReductionMultiplier = 0.30;
        this.objectCoinValue = 40;
        this.objectName = "Golden Armor";
        this.objectDescription = "[" + this.objectName + "]\nAn legendary golden armor.\n+" + (this.objectDamageReductionMultiplier * 100) + "% Armor.";
    }
}
