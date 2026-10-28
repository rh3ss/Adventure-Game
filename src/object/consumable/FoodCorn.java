package object.consumable;

import enums.ObjectType;
import main.GamePanel;

public class FoodCorn extends Consumable {

    public FoodCorn(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.FOOD_CORN;
        this.down1 = this.setupEntityImage("/res/objects/corn.png", gamePanel.tileSize, gamePanel.tileSize);
        this.objectCoinValue = 5;
        this.objectName = "Corn";
        this.objectDescription = "Short and sweet " + this.objectName + ".";
    }
}
