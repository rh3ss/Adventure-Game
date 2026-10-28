package object.consumable;

import enums.ObjectType;
import main.GamePanel;

public class FoodPear extends Consumable {

    public FoodPear(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.FOOD_PEAR;
        this.down1 = this.setupEntityImage("/res/objects/pear.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectCoinValue = 5;
        this.objectName = "Pear";
        this.objectDescription = "A " + this.objectName + " from a tree.";
    }
}
