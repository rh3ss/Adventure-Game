package object.interactable;

import entity.Entity;
import enums.GameState;
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

    public void use(Entity entity) {
        this.gamePanel.gameState = GameState.DIALOGUE;
        int objectIndex = this.getDetectedObjectNearby(entity, this.gamePanel.maps.get(this.gamePanel.currentMapNumber), ObjectType.DOOR);
        if (objectIndex != Integer.MAX_VALUE) {
            this.gamePanel.gui.currentDialogueMessage = "You use the " + this.objectName + " to open the door.";
            this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.set(objectIndex, null);
        }
        else {
            this.gamePanel.gui.currentDialogueMessage = "What are you doing?";
        }
    }
}
