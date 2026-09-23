package npc;

import enums.GameState;
import main.GamePanel;
import object.armor.ArmorGold;
import object.armor.ArmorIron;
import object.shield.ShieldBlue;
import object.shield.ShieldWood;
import object.weapon.SwordGold;
import object.weapon.SwordIron;

import java.util.ArrayList;

public class Blacksmith extends NPC {

    public Blacksmith(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;

        this.getImages();
        this.setDialogues();
        this.setInventory();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/npc/blacksmith_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/npc/blacksmith_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/npc/blacksmith_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/npc/blacksmith_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/npc/blacksmith_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/npc/blacksmith_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/npc/blacksmith_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/npc/blacksmith_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    public void setDialogues() {
        this.dialogues.add("Hello, stranger. I'm a talented blacksmith \n" +
                           "and offer reliable equipment. \n" +
                           "Are you interested?");
    }

    private void setInventory() {
        // inventory
        this.inventory = new ArrayList<>();
        this.inventoryColumnSize = 5;
        this.inventoryRowSize = 4;
        this.maxInventorySize = this.inventoryColumnSize * this.inventoryRowSize;

        this.inventory.add(new SwordIron(this.gamePanel, -1, -1));
        this.inventory.add(new SwordGold(this.gamePanel, -1, -1));
        this.inventory.add(new ShieldWood(this.gamePanel, -1, -1));
        this.inventory.add(new ShieldBlue(this.gamePanel, -1, -1));
        this.inventory.add(new ArmorIron(this.gamePanel, -1, -1));
        this.inventory.add(new ArmorGold(this.gamePanel, -1, -1));
    }

    public void speak() {
        super.speak();

        this.gamePanel.gameState = GameState.TRADING;
        this.gamePanel.gui.interactedNPC = this;
    }
}
