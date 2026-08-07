package object;

import entity.Entity;
import enums.EntityTyp;
import enums.ObjectTyp;
import main.GamePanel;

public class ObjectBoots extends Entity {

    public ObjectBoots(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityTyp = EntityTyp.OBJECT;
        this.objectTyp = ObjectTyp.BOOTS;
        this.down1 = this.setupEntityImage("/res/objects/boots.png");
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
