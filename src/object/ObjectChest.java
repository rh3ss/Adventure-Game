package object;

import entity.Entity;
import enums.EntityTyp;
import enums.ObjectTyp;
import main.GamePanel;

public class ObjectChest extends Entity {

    public ObjectChest(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityTyp = EntityTyp.OBJECT;
        this.objectTyp = ObjectTyp.CHEST;
        this.down1 = this.setupEntityImage("/res/objects/chest.png");
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
