package object;

import entity.Entity;
import enums.EntityType;
import main.GamePanel;

import java.awt.Color;

public abstract class GameObject extends Entity {
    public String objectName = "", objectDescription = "";
    public Color objectColor;
    public int objectBenefitValue, objectCoinPrice;
    public double objectAttackDamageMultiplier, objectDamageReductionMultiplier;

    public GameObject(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
