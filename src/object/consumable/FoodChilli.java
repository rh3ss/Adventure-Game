package object.consumable;

import enums.ObjectType;
import main.GamePanel;

public class FoodChilli extends Consumable {

    public FoodChilli(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.FOOD_CHILLI;
        this.down1 = this.setupEntityImage("/res/objects/chilli.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectCoinValue = 5;
        this.objectName = "Chilli";
        this.objectDescription = "Hot and spicy " + this.objectName + ".";
    }
}
