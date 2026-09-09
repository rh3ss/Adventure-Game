package object.shield;

import enums.ObjectType;
import main.GamePanel;

public class ShieldBlue extends Shield {

    public ShieldBlue(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.SHIELD_BLUE;
        this.down1 = this.setupEntityImage("/res/objects/shield_blue.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDamageReductionMultiplier = 0.4;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn epic blue shield.\n+" + (this.objectDamageReductionMultiplier * 100) + "% Defense reduction.";
    }
}
