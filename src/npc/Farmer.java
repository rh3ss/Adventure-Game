package npc;

import enums.GameState;
import main.GamePanel;
import object.consumable.PotionHeal;

public class Farmer extends NPC {

    public Farmer(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;
        this.isFixPlaced = true;

        this.getImages();
        this.setDialogues();
        this.setInventory();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/npc/farmer_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/npc/farmer_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/npc/farmer_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/npc/farmer_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/npc/farmer_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/npc/farmer_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/npc/farmer_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/npc/farmer_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public void setDialogues() {
        this.dialogues.add("Hello, I'm a local farmer offering fresh \n" +
                           "food. Would you like to support me? ");
    }

    public void setInventory() {
        super.setInventory();
        // inventory
        this.inventory.add(new PotionHeal(this.gamePanel, -1, -1));
    }

    public void speak() {
        super.speak();

        this.gamePanel.gameState = GameState.TRADING;
        this.gamePanel.gui.interactedNPC = this;
    }
}
