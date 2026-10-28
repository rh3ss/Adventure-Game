package object.consumable;

import enums.ObjectType;
import main.GamePanel;

public class FoodApple extends Consumable {

    public FoodApple(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.FOOD_APPLE;
        this.down1 = this.setupEntityImage("/res/objects/apple.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectCoinValue = 5;
        this.objectName = "Apple";
        this.objectDescription = "An " + this.objectName + " from a tree.";
    }
}
