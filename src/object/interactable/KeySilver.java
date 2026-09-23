package object.interactable;

import enums.ObjectType;
import main.GamePanel;

public class KeySilver extends Interactable {

    public KeySilver(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.KEY;
        this.down1 = this.setupEntityImage("/res/objects/key_silver.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectCoinValue = 3;
        this.objectName = "Silver Key";
        this.objectDescription = "[" + this.objectName + "]\nA silver key.";
    }
}
