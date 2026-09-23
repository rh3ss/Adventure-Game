package object.shield;

import enums.ObjectType;
import main.GamePanel;

public class ShieldWood extends Shield {

    public ShieldWood(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.SHIELD;
        this.down1 = this.setupEntityImage("/res/objects/shield_wood.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectDamageReductionMultiplier = 0.2;
        this.objectCoinValue = 15;
        this.objectName = "Wooden Shield";
        this.objectDescription = "[" + this.objectName + "]\nAn old wooden shield.\n+" + (this.objectDamageReductionMultiplier * 100) + "% Defense reduction.";
    }
}
