package object.obstacle;

import enums.GameState;
import enums.ObjectType;
import main.GamePanel;
import object.GameObject;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Chest extends Obstacle {
    private final ArrayList<GameObject> loot;
    private boolean opened;

    public Chest(GamePanel gamePanel, int worldColumn, int worldRow, ArrayList<GameObject> loot) {
        super(gamePanel, worldColumn, worldRow);
        this.loot = loot;
        this.opened = false;

        this.objectType = ObjectType.CHEST;
        this.image1 = this.setupEntityImage("/res/objects/chest.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.image2 = this.setupEntityImage("/res/objects/chest_opened.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.image1;
        this.objectName = "Chest";

        this.isSolid = true;
        this.solidArea = new Rectangle(4, 16, 40, 32);
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }

    public void interact() {
        if (!this.opened) {
            this.gamePanel.gameState = GameState.DIALOGUE;
            this.gamePanel.gui.currentDialogueMessage = "You open the chest and find great loot!";
            int numLoot = this.loot.size();
            int chestX = this.worldX;
            int chestY = this.worldY;
            int radius = this.gamePanel.tileSize;
            // place loot in half circle below chest
            for (int i = 0; i < numLoot; i++) {
                double angle = Math.PI * i / Math.max(1, numLoot - 1);
                GameObject object = this.loot.get(i);
                object.worldX = chestX + (int) (Math.cos(angle) * radius);
                object.worldY = chestY + (int) (Math.sin(angle) * radius);
                this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(object);
            }
            // chest can not be open again
            this.down1 = this.image2;
            this.opened = true;
        }
    }
}
