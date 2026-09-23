package npc;

import entity.Entity;
import enums.Direction;
import enums.EntityType;
import main.GamePanel;

public abstract class NPC extends Entity {

    public NPC(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.NPC;
        this.isSolid = true;
        this.speechBubble = setupEntityImage("/res/objects/speech_bubble.png", gamePanel.tileSize, gamePanel.tileSize);
        this.activationRadius = gamePanel.tileSize * 3;
    }

    public abstract void getImages();

    public abstract void setDialogues();

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
