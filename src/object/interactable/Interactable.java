package object.interactable;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;

public abstract class Interactable extends GameObject {

    public Interactable(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectCategory = ObjectCategory.INTERACTABLE;
    }
}
