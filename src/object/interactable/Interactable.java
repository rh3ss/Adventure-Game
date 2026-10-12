package object.interactable;

import entity.Entity;
import enums.GameState;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;
import object.GameObject;

public abstract class Interactable extends GameObject {
    public GamePanel gamePanel;

    public Interactable(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.gamePanel = gamePanel;

        this.objectCategory = ObjectCategory.INTERACTABLE;
        this.isTradable = false;
        this.isStackable = true;
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
            this.gamePanel.gui.currentDialogueMessage = "Use the " + this.objectName + " nearby a door.";
        }
        return successful;
    }
}
