package object.armor;

import enums.ObjectType;
import main.GamePanel;

public class ArmorIron extends Armor {

    public ArmorIron(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.ARMOR;
        this.down1 = this.setupEntityImage("/res/objects/armor_iron.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDamageReductionMultiplier = 0.15;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn good robust iron armor.\n+" + (this.objectDamageReductionMultiplier * 100) + "% Armor.";
    }
}
