package object.armor;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;


public class Armor extends GameObject {

    public Armor(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectCategory = ObjectCategory.ARMOR;
    }
}
