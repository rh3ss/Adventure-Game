package object.consumable;

import enums.ObjectType;
import main.GamePanel;

public class FoodCarrot extends Consumable {

    public FoodCarrot(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.FOOD_CARROT;
        this.down1 = this.setupEntityImage("/res/objects/carrot.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectCoinValue = 5;
        this.objectName = "Carrot";
        this.objectDescription = "A ripe and crunchy " + this.objectName + ".";
    }
}
