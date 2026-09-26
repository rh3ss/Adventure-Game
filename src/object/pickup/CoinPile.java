package object.pickup;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

public class CoinPile extends PickUp {

    public CoinPile(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.COIN;
        this.down1 = this.setupEntityImage("/res/objects/coin_pile.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.objectBenefitValue = 5;
        this.objectName = "Coin Pile";
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectName + " +" + this.objectBenefitValue, this.objectColor);
        entity.coins += this.objectBenefitValue;
    }
}
