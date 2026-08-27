package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

public class ObjectShieldBlue extends Entity {

    public ObjectShieldBlue(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.SHIELD;
        this.objectType = ObjectType.SHIELD_BLUE;
        this.down1 = this.setupEntityImage("/res/objects/shield_blue.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectDefenseValue = 2;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn epic blue shield.";
    }
}
