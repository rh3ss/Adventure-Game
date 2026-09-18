package object.armor;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import main.GamePanel;


public class Armor extends Entity {

    public Armor(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.ARMOR;
    }
}
