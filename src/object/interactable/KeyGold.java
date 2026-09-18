package object.interactable;

import enums.ObjectType;
import main.GamePanel;

public class KeyGold extends Interactable {

    public KeyGold(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.KEY;
        this.down1 = this.setupEntityImage("/res/objects/key_gold.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectDescription = "[" + this.objectType.toString() + "]\nA golden key.";
    }
}
