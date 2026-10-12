package object.armor;

import enums.ObjectType;
import main.GamePanel;

public class ArmorIron extends Armor {

    public ArmorIron(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.ARMOR;
        this.down1 = this.setupEntityImage("/res/objects/armor_iron.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectDamageReductionMultiplier = 0.15;
        this.objectCoinValue = 25;
        this.objectName = "Iron Armor";
        this.objectDescription = "An robust " + this.objectName + ".\n+" + (this.objectDamageReductionMultiplier * 100) + "% Armor.";
    }
}
