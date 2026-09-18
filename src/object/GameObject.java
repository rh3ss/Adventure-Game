package object;

import entity.Entity;
import enums.EntityType;
import main.GamePanel;

import java.awt.Color;

public class GameObject extends Entity {
    public String objectName = "", objectDescription = "";
    public Color objectColor;
    public int objectBenefitValue;
    public double objectAttackDamageMultiplier, objectDamageReductionMultiplier;

    public GameObject(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
    }
}
