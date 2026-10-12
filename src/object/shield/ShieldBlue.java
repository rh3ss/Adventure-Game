package object.shield;

import enums.ObjectType;
import main.GamePanel;

public class ShieldBlue extends Shield {

    public ShieldBlue(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.SHIELD;
        this.down1 = this.setupEntityImage("/res/objects/shield_blue.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectDamageReductionMultiplier = 0.35;
        this.objectCoinValue = 40;
        this.objectName = "Knight Shield";
        this.objectDescription = "An epic " + this.objectName + ".\n+" + (this.objectDamageReductionMultiplier * 100) + "% Defense reduction.";
    }
}
