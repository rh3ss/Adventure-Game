package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

public class ObjectCoin extends Entity {

    public ObjectCoin(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.PICKUP;
        this.objectType = ObjectType.COIN;
        this.down1 = this.setupEntityImage("/res/objects/coin.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;

        this.objectBenefitValue = 1;
    }

    public void use(Entity entity) {
        this.gamePanel.gui.addMessage(this.objectType.toString() + " +" + this.objectBenefitValue);
        this.gamePanel.player.coins += this.objectBenefitValue;
    }
}
