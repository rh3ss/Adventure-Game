package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectType;
import main.GamePanel;

public class ObjectSword extends Entity {

    public ObjectSword(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectType = ObjectType.SWORD;
        this.down1 = this.setupEntityImage("/res/objects/sword_normal.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.objectAttackValue = 1;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn old wooden sword.";
    }
}
