package object;

import entity.Entity;
import enums.EntityTyp;
import enums.ObjectTyp;
import main.GamePanel;
import java.awt.Rectangle;

public class ObjectDoor extends Entity {

    public ObjectDoor(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityTyp = EntityTyp.OBJECT;
        this.objectTyp = ObjectTyp.DOOR;
        this.down1 = this.setupEntityImage("/res/objects/door.png");
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.isSolid = true;
        int entityCollisionOffset = 8;
        this.solidArea = new Rectangle(
            0, entityCollisionOffset * 2, this.gamePanel.tileSize, this.gamePanel.tileSize - (entityCollisionOffset * 2)
        );
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
