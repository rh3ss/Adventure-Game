package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

import java.awt.*;

public class ObjectHeart extends Entity {

    public ObjectHeart(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.PICKUP;
        this.objectType = ObjectType.HEART;
        this.down1 = this.setupEntityImage("/res/objects/heart_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image1 = this.setupEntityImage("/res/objects/heart_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image2 = this.setupEntityImage("/res/objects/heart_blank.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;

        this.objectBenefitValue = 1;
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectType.toString() + " +" + this.objectBenefitValue, Color.WHITE);
        entity.currentHearts += this.objectBenefitValue;
    }
}
