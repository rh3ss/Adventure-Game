package object;

import entity.Entity;
import enums.EntityTyp;
import enums.ObjectTyp;
import main.GamePanel;

public class ObjectKey extends Entity {

    public ObjectKey(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityTyp = EntityTyp.OBJECT;
        this.objectTyp = ObjectTyp.KEY;
        this.down1 = this.setupEntityImage("/res/objects/key.png");
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
