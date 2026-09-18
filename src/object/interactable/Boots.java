package object.interactable;

import enums.ObjectType;
import main.GamePanel;

public class Boots extends Interactable {

    public Boots(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.BOOTS;
        this.down1 = this.setupEntityImage("/res/objects/boots.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
