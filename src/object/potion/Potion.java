package object.potion;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class Potion extends Entity {
    public final GamePanel gamePanel;
    public Color color;

    public Potion(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.CONSUMABLE;
        this.objectType = ObjectType.POTION;
    }

    public void use(Entity entity) { }
}
