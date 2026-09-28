package object.potion;

import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;
import object.GameObject;


public abstract class Potion extends GameObject {
    public final GamePanel gamePanel;

    public Potion(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.gamePanel = gamePanel;

        this.objectCategory = ObjectCategory.CONSUMABLE;

        this.isStackable = true;
        this.objectMaxStackableAmount = 10;
    }
}
