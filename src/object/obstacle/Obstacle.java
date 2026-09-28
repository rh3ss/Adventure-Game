package object.obstacle;

import enums.ObjectCategory;
import main.GamePanel;
import object.GameObject;

import java.awt.Rectangle;


public abstract class Obstacle extends GameObject {

    public Obstacle(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectCategory = ObjectCategory.OBSTACLE;
        this.isSolid = true;
        this.solidArea = new Rectangle(0, 16, 48, 32);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
