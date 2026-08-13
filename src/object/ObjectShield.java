package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectType;
import main.GamePanel;

public class ObjectShield extends Entity {

    public ObjectShield(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectType = ObjectType.SHIELD;
        this.down1 = this.setupEntityImage("/res/objects/shield_wood.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDefenseValue = 1;
    }
}
