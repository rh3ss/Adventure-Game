package object.shield;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;


public abstract class Shield extends GameObject {

    public Shield(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectCategory = ObjectCategory.SHIELD;
    }
}
