package object;

import entity.Entity;
import enums.EntityTyp;
import enums.ObjectTyp;
import main.GamePanel;

public class ObjectHeart extends Entity {

    public ObjectHeart(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityTyp = EntityTyp.OBJECT;
        this.objectTyp = ObjectTyp.HEART;
        this.image1 = this.setupEntityImage("/res/objects/heart_full.png");
        this.image2 = this.setupEntityImage("/res/objects/heart_blank.png");
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
