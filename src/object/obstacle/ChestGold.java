package object.obstacle;

import main.GamePanel;
import object.GameObject;

import java.util.ArrayList;

public class ChestGold extends Chest {

    public ChestGold(GamePanel gamePanel, int worldColumn, int worldRow, ArrayList<GameObject> loot) {
        super(gamePanel, worldColumn, worldRow, loot);

        this.image1 = this.setupEntityImage("/res/objects/chest_gold.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image2 = this.setupEntityImage("/res/objects/chest_gold_opened.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.image1;
        this.objectName = "Gold Chest";
    }
}
