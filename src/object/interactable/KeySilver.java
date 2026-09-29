package object.interactable;

import entity.Entity;
import enums.GameState;
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

    public boolean useSuccessful(Entity entity) {
        boolean successful = false;
        this.gamePanel.gameState = GameState.DIALOGUE;
        int objectIndex = this.getDetectedObjectNearby(entity, this.gamePanel.maps.get(this.gamePanel.currentMapNumber), ObjectType.DOOR);
        if (objectIndex != Integer.MAX_VALUE) {
            this.gamePanel.gui.currentDialogueMessage = "You use the " + this.objectName + " to open the door.";
            this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(objectIndex).down1 = this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(objectIndex).image2;
            this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(objectIndex).worldY -= this.gamePanel.tileSize;
            this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.get(objectIndex).isSolid = false;
            successful = true;
        }
        else {
            this.gamePanel.gui.currentDialogueMessage = "What are you doing?";
        }
        return successful;
    }
}
