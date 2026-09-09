package object.shield;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import main.GamePanel;


public class Shield extends Entity {

    public Shield(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.SHIELD;
    }
}
