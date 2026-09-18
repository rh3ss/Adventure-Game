package object.interactable;

import enums.ObjectType;
import main.GamePanel;

public class Wood extends Interactable {

    public Wood(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.WOOD;
        this.down1 = this.setupEntityImage("/res/objects/wood.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectDescription = "[" + this.objectType.toString() + "]\nWood from a tree.";
    }
}
