package object.interactable;

import enums.ObjectType;
import main.GamePanel;

public class Boots extends Interactable {

    public Boots(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.BOOTS;
        this.down1 = this.setupEntityImage("/res/objects/boots.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectCoinValue = 5;
        this.objectName = "Boots";
        this.objectDescription = "[" + this.objectName + "]\n";
    }
}
