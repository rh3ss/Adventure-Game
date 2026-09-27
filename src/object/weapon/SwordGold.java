package object.weapon;

import enums.ObjectType;
import main.GamePanel;

import java.awt.Rectangle;


public class SwordGold extends Weapon {

    public SwordGold(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.SWORD;
        this.down1 = this.setupEntityImage("/res/objects/sword_gold.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.attackArea = new Rectangle(0, 0, 36, 36);
        this.objectAttackDamageMultiplier = 0.2;
        this.knockBackPower = 5;
        this.attackStartAngle = 35;
        this.attackEndAngle = -35;
        this.objectCoinValue = 20;
        this.objectName = "Golden Sword";
        this.objectDescription = "[" + this.objectName + "]\nAn old golden sword.\n+" + (this.objectAttackDamageMultiplier * 100) + "% Attack damage.";
    }
}
