package object;

import entity.Entity;
import enums.EntityType;
import enums.ObjectCategory;
import enums.ObjectType;
import main.GamePanel;

import java.awt.*;

public class ObjectAxe extends Entity {

    public ObjectAxe(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.objectCategory = ObjectCategory.WEAPON;
        this.objectType = ObjectType.AXE;
        this.down1 = this.setupEntityImage("/res/objects/axe.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.attackArea = new Rectangle(0, 0, 30, 30);
        this.objectAttackDamageMultiplier = 0.3;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn heavy lumberjack axe.\n+" + (this.objectAttackDamageMultiplier * 100) + "% Attack damage.";
    }
}
