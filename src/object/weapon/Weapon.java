package object.weapon;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;


public abstract class Weapon extends GameObject {

    public Weapon(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectCategory = ObjectCategory.WEAPON;
    }
}
