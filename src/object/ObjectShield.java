package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

public class ObjectShield extends Entity {

    public ObjectShield(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.SHIELD;
        this.objectType = ObjectType.SHIELD_WOOD;
        this.down1 = this.setupEntityImage("/res/objects/shield_wood.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDefenseValue = 1;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn old wooden shield.";
    }
}
