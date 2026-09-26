package object.pickup;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

public class Coin extends PickUp {

    public Coin(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.COIN;
        this.down1 = this.setupEntityImage("/res/objects/coin.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectBenefitValue = 1;
        this.objectName = "Coin";
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectName + " +" + this.objectBenefitValue, this.objectColor);
        entity.coins += this.objectBenefitValue;
    }
}
