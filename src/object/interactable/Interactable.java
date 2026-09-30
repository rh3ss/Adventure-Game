package object.interactable;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;

public abstract class Interactable extends GameObject {
    public GamePanel gamePanel;

    public Interactable(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.gamePanel = gamePanel;

        this.objectCategory = ObjectCategory.INTERACTABLE;
        this.isTradable = false;
        this.isStackable = true;
    }
}
