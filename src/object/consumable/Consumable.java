package object.consumable;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;


public abstract class Consumable extends GameObject {
    public final GamePanel gamePanel;

    public Consumable(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.gamePanel = gamePanel;

        this.objectCategory = ObjectCategory.CONSUMABLE;
        this.isStackable = true;
    }
}
