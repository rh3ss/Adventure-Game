package object.potion;

import entity.Entity;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;
import object.GameObject;


public class Potion extends GameObject {
    public final GamePanel gamePanel;

    public Potion(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.gamePanel = gamePanel;

        this.objectCategory = ObjectCategory.CONSUMABLE;
        this.objectType = ObjectType.POTION;
    }

    public void use(Entity entity) { }
}
