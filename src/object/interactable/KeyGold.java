package object.interactable;

import enums.ObjectType;
import main.GamePanel;

public class KeyGold extends Interactable {

    public KeyGold(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.KEY;
        this.down1 = this.setupEntityImage("/res/objects/key_gold.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectCoinValue = 5;
        this.objectName = "Golden Key";
        this.objectDescription = "[" + this.objectName + "]\nA golden key.";
    }
}
