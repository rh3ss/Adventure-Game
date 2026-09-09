package object.pickup;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class PickUpHeart extends PickUp {

    public PickUpHeart(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.HEART;
        this.down1 = this.setupEntityImage("/res/objects/heart_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image1 = this.setupEntityImage("/res/objects/heart_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image2 = this.setupEntityImage("/res/objects/heart_blank.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.color = Color.WHITE;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;

        this.objectBenefitValue = 1;
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectType.toString() + " +" + this.objectBenefitValue, this.color);
        entity.currentHearts += this.objectBenefitValue;
    }
}
