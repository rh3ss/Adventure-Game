package npc;

import entity.Entity;
import enums.Direction;
import enums.EntityType;
import main.GamePanel;
import object.potion.PotionHeal;

import java.util.ArrayList;

public abstract class NPC extends Entity {
    public boolean isFixPlaced;

    public NPC(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.NPC;
        this.isSolid = true;
        this.isFixPlaced = false;
        this.speechBubble = setupEntityImage("/res/objects/speech_bubble.png", gamePanel.tileSize, gamePanel.tileSize);
        this.activationRadius = gamePanel.tileSize * 3;
    }

    public abstract void getImages();

    public abstract void setDialogues();

    public void setInventory() {
        // inventory
        this.inventory = new ArrayList<>();
        this.inventoryColumnSize = 5;
        this.inventoryRowSize = 4;
        this.maxInventorySize = this.inventoryColumnSize * this.inventoryRowSize;
    }

    public void speak() {
        this.gamePanel.gui.currentDialogueMessage = this.dialogues.get(this.dialogueIndex);
        this.dialogueIndex++;
        if (this.dialogueIndex > this.dialogues.size() - 1) {
            this.dialogueIndex = 0;
        }
        // entity should look in players direction while dialogue
        switch (this.gamePanel.player.direction) {
            case Direction.UP -> { this.direction = Direction.DOWN; }
            case Direction.DOWN -> { this.direction = Direction.UP; }
            case Direction.LEFT -> { this.direction = Direction.RIGHT; }
            case Direction.RIGHT -> { this.direction = Direction.LEFT; }
        }
    }

}
