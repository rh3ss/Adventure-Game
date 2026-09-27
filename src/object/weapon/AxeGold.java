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
        this.knockBackPower = 10;
        this.attackStartAngle = 45;
        this.attackEndAngle = -45;
        this.objectCoinValue = 30;
        this.objectName = "Golden Axe";
        this.objectDescription = "[" + this.objectName + "]\nAn golden lumberjack axe.\n+" + (this.objectAttackDamageMultiplier * 100) + "% Attack damage.";
    }
}
