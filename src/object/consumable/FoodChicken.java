package object.consumable;

import enums.ObjectType;
import main.GamePanel;

public class FoodChicken extends Consumable {

    public FoodChicken(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.FOOD_CHICKEN;
        this.down1 = this.setupEntityImage("/res/objects/chicken.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectCoinValue = 10;
        this.objectName = "Chicken";
        this.objectDescription = "Delicious " + this.objectName + " with Protein.";
    }
}
