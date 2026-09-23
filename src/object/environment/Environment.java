package object.environment;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;

public abstract class Environment extends GameObject {

    public Environment(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectCategory = ObjectCategory.ENVIRONMENT;
    }
}
