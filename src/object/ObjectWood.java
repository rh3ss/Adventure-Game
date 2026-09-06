package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

public class ObjectWood extends Entity {

    public ObjectWood(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.INTERACTABLE;
        this.objectType = ObjectType.WOOD;
        this.down1 = this.setupEntityImage("/res/objects/wood.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDescription = "[" + this.objectType.toString() + "]\nWood from a tree.";
    }
}
