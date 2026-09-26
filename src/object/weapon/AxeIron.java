package object.weapon;

import enums.ObjectType;
import main.GamePanel;

import java.awt.Rectangle;


public class AxeIron extends Weapon {

    public AxeIron(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.AXE;
        this.down1 = this.setupEntityImage("/res/objects/axe_iron.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackArea = new Rectangle(0, 0, 30, 30);
        this.objectAttackDamageMultiplier = 0.3;
        this.knockBackPower = 10;
        this.objectCoinValue = 10;
        this.objectName = "Iron Axe";
        this.objectDescription = "[" + this.objectName + "]\nAn heavy lumberjack axe.\n+" + (this.objectAttackDamageMultiplier * 100) + "% Attack damage.";
    }
}
