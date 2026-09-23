package npc;

import enums.GameState;
import main.GamePanel;
import object.potion.PotionExperience;
import object.potion.PotionHeal;
import object.potion.PotionStrength;

import java.util.ArrayList;

public class Trader extends NPC {

    public Trader(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;

        this.getImages();
        this.setDialogues();
        this.setInventory();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/npc/trader_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/npc/trader_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/npc/trader_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/npc/trader_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/npc/trader_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/npc/trader_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/npc/trader_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/npc/trader_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public void setDialogues() {
        this.dialogues.add("Hello, I'm a wandering trader offering \n" +
                           "valuable items. Would you like to trade?");
    }

    private void setInventory() {
        // inventory
        this.inventory = new ArrayList<>();
        this.inventoryColumnSize = 5;
        this.inventoryRowSize = 4;
        this.maxInventorySize = this.inventoryColumnSize * this.inventoryRowSize;

        this.inventory.add(new PotionExperience(this.gamePanel, -1, -1));
        this.inventory.add(new PotionStrength(this.gamePanel, -1, -1));
        this.inventory.add(new PotionHeal(this.gamePanel, -1, -1));
    }

    public void speak() {
        super.speak();

        this.gamePanel.gameState = GameState.TRADING;
        this.gamePanel.gui.interactedNPC = this;
    }
}
