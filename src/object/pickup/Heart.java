package object.pickup;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

public class Heart extends PickUp {

    public Heart(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.HEART;
        this.down1 = this.setupEntityImage("/res/objects/heart_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image1 = this.setupEntityImage("/res/objects/heart_full.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image2 = this.setupEntityImage("/res/objects/heart_blank.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectBenefitValue = 1;
        this.objectName = "Heart";
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectName + " +" + this.objectBenefitValue, this.objectColor);
        entity.currentHearts += this.objectBenefitValue;
    }
}
