package object.weapon;

import enums.ObjectType;
import main.GamePanel;

import java.awt.Rectangle;


public class AxeGold extends Weapon {

    public AxeGold(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.AXE;
        this.down1 = this.setupEntityImage("/res/objects/axe_gold.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackArea = new Rectangle(0, 0, 30, 30);
        this.objectAttackDamageMultiplier = 0.4;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn golden lumberjack axe.\n+" + (this.objectAttackDamageMultiplier * 100) + "% Attack damage.";
    }
}
