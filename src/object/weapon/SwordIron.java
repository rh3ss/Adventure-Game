package object.weapon;

import enums.ObjectType;
import main.GamePanel;

import java.awt.Rectangle;


public class SwordIron extends Weapon {

    public SwordIron(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.SWORD;
        this.down1 = this.setupEntityImage("/res/objects/sword_iron.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.attackArea = new Rectangle(0, 0, 36, 36);
        this.objectAttackDamageMultiplier = 0.15;
        this.objectDescription = "[" + this.objectType.toString() + "]\nAn good robust iron sword.\n+" + (this.objectAttackDamageMultiplier * 100) + "% Attack damage.";
    }
}
