package object.weapon;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import main.GamePanel;


public class Weapon extends Entity {

    public Weapon(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.WEAPON;
    }
}
