package object.pickup;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;

import java.awt.Color;


public abstract class PickUp extends GameObject {
    public final GamePanel gamePanel;

    public PickUp(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);
        this.gamePanel = gamePanel;

        this.objectCategory = ObjectCategory.PICKUP;
        this.objectColor = Color.WHITE;
        this.isTradable = false;
    }
}
