package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

public class ObjectKeySilver extends Entity {

    public ObjectKeySilver(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.INTERACTABLE;
        this.objectType = ObjectType.KEY;
        this.down1 = this.setupEntityImage("/res/objects/key_silver.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDescription = "[" + this.objectType.toString() + "]\nA silver key.";
    }
}
