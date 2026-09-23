package object.environment;

import enums.ObjectType;
import main.GamePanel;

public class Wood extends Environment {

    public Wood(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.WOOD;
        this.down1 = this.setupEntityImage("/res/objects/wood.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectCoinValue = 4;
        this.objectName = "Wood";
        this.objectDescription = "[" + this.objectName + "]\nWood from a tree.";
    }
}
