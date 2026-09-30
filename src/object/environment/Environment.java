package object.environment;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;

public abstract class Environment extends GameObject {
    public GamePanel gamePanel;

    public Environment(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.gamePanel = gamePanel;

        this.objectCategory = ObjectCategory.ENVIRONMENT;
        this.isStackable = true;
    }
}
