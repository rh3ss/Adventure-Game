package object.weapon;

import enums.ObjectType;
import main.GamePanel;

import java.awt.Rectangle;


public class AxeIron extends Weapon {

    public AxeIron(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.AXE;
        this.down1 = this.setupEntityImage("/res/objects/axe.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.attackArea = new Rectangle(0, 0, 30, 30);
        this.objectAttackDamageMultiplier = 0.3;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn heavy lumberjack axe.\n+" + (this.objectAttackDamageMultiplier * 100) + "% Attack damage.";
    }
}
