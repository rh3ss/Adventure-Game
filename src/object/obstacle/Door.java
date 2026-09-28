package object.obstacle;

import enums.GameState;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Rectangle;

public class Door extends Obstacle {

    public Door(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.DOOR;
        this.down1 = this.setupEntityImage("/res/objects/door.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectName = "Door";

        this.isSolid = true;
        this.solidArea = new Rectangle(0, 16, 48, 32);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }

    public void interact() {
        this.gamePanel.gameState = GameState.DIALOGUE;
        this.gamePanel.gui.currentDialogueMessage = "You need a key to open this";
    }
}
