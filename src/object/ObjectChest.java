package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectType;
import main.GamePanel;

public class ObjectChest extends Entity {

    public ObjectChest(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectType = ObjectType.CHEST;
        this.down1 = this.setupEntityImage("/res/objects/chest.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
