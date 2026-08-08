package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectType;
import main.GamePanel;

public class ObjectBoots extends Entity {

    public ObjectBoots(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectType = ObjectType.BOOTS;
        this.down1 = this.setupEntityImage("/res/objects/boots.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
