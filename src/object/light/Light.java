package object.light;

import enums.EntityType;
import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;

public class Light extends GameObject {
    public int lightRadius;

    public Light(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.LIGHT;
    }
}
