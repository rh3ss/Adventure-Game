package object.pickup;

import entity.Entity;
import enums.ObjectType;
import main.GamePanel;

import java.awt.Color;

public class PickUpCoin extends PickUp {

    public PickUpCoin(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.COIN;
        this.down1 = this.setupEntityImage("/res/objects/coin1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.color = Color.WHITE;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;

        this.objectBenefitValue = 1;
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectType.toString() + " +" + this.objectBenefitValue, this.color);
        this.gamePanel.player.coins += this.objectBenefitValue;
    }
}
