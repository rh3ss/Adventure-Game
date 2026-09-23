package object.pickup;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class PickUpCoin extends PickUp {

    public PickUpCoin(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.COIN;
        this.down1 = this.setupEntityImage("/res/objects/coin.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectColor = Color.WHITE;
        this.objectBenefitValue = 1;
        this.objectName = "Coin";
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectType.toString() + " +" + this.objectBenefitValue, this.objectColor);
        entity.coins += this.objectBenefitValue;
    }
}
