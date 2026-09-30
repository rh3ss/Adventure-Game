package object;

import entity.Entity;
import enums.EntityType;
import main.GamePanel;

import java.awt.Color;

public abstract class GameObject extends Entity {
    public String objectName, objectDescription;
    public Color objectColor;
    public int objectBenefitValue, objectCoinValue;
    public int objectCurrentStackableAmount;
    public double objectAttackDamageMultiplier, objectDamageReductionMultiplier;
    public boolean isTradable, isStackable;

    public GameObject(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.entityType = EntityType.OBJECT;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;

        this.objectName = this.objectDescription = "";
        this.objectCurrentStackableAmount = 1;
        this.isTradable = true;
        this.isStackable = false;
    }

    public void use(Entity entity) {}
    public boolean useSuccessful(Entity entity) { return false; }
    public void interact() {}
}
