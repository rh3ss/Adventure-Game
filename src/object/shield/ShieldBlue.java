package object.shield;

import enums.ObjectType;
import main.GamePanel;

public class ShieldBlue extends Shield {

    public ShieldBlue(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.SHIELD;
        this.down1 = this.setupEntityImage("/res/objects/shield_blue.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectDamageReductionMultiplier = 0.4;
        this.objectCoinValue = 30;
        this.objectName = "Knight Shield";
        this.objectDescription = "[" + this.objectName + "]\nAn epic knight shield.\n+" + (this.objectDamageReductionMultiplier * 100) + "% Defense reduction.";
    }
}
