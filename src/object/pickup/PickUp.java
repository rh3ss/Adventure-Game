package object.pickup;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import main.GamePanel;

import java.awt.Color;

public class PickUp extends Entity {
    public final GamePanel gamePanel;
    public Color color;

    public PickUp(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);
        this.gamePanel = gamePanel;

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.PICKUP;
    }

    public void use(Entity entity) { }
}
