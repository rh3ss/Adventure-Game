package object.interactable;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import main.GamePanel;

public class Interactable extends Entity {

    public Interactable(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.INTERACTABLE;
    }
}
