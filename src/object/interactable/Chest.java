package object.interactable;

import enums.ObjectType;
import main.GamePanel;

import java.awt.*;

public class Chest extends Interactable {

    public Chest(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.CHEST;
        this.down1 = this.setupEntityImage("/res/objects/chest.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;

        this.isSolid = true;
        this.solidArea = new Rectangle(0, 16, 48, 32);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
}
