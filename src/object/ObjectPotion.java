package object;

import entity.Entity;
import enums.EntityType;
import enums.GameState;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

public class ObjectPotion extends Entity {
    private GamePanel gamePanel;
    private final int HEALING_POTION_HEAL_AMOUNT = 2;

    public ObjectPotion(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.CONSUMABLE;
        this.objectType = ObjectType.POTION;
        this.down1 = this.setupEntityImage("/res/objects/potion_red.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDefenseValue = 1;
        this.objectDescription = "[" + this.objectType.toString() + "]\nHeals your life by " + this.HEALING_POTION_HEAL_AMOUNT + " hearts.";
    }

    public void use(Entity entity) {
        this.gamePanel.gameState = GameState.DIALOGUE;
        this.gamePanel.gui.currentDialogueMessage = "You drink the " + this.objectType.toString() + "!\n"
                + "Your hearts has been recovered by " + this.HEALING_POTION_HEAL_AMOUNT + ".";
        entity.currentHearts += this.HEALING_POTION_HEAL_AMOUNT;
        if (this.gamePanel.player.currentHearts > this.gamePanel.player.maxHearts) {
            this.gamePanel.player.currentHearts = this.gamePanel.player.maxHearts;
        }
    }
}
