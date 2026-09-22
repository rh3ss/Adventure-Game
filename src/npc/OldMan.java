package npc;


import entity.Entity;
import enums.Direction;
import enums.EntityType;
import main.GamePanel;

import java.util.Random;

public class OldMan extends Entity {

    public OldMan(GamePanel p, int worldColumn, int worldRow) {
        super(p);

        this.entityType = EntityType.NPC;
        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;
        this.velocity = 1;
        this.isSolid = true;

        this.getImages();
        this.setDialogues();
    }

    private void getImages() {
        this.up1 = this.setupEntityImage("/res/npc/oldman_up_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/npc/oldman_up_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/npc/oldman_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/npc/oldman_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/npc/oldman_left_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/npc/oldman_left_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/npc/oldman_right_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/npc/oldman_right_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }

    private void setDialogues() {
        this.dialogues.add("Hello man.");
        this.dialogues.add("So you've come to this island to \nfind the treasure?");
        this.dialogues.add("I used to be a great wizard but now... \nI'm a bit too old for taking an adventure.");
        this.dialogues.add("Well, good luck on you.");
    }

    public void setAction() {
        this.actionCounterFrames++;
        if (this.actionCounterFrames > 120) {
            Random r = new Random();
            double number  = r.nextDouble();

            if (number <= 0.25) { this.direction = Direction.UP; }
            else if (number <= 0.50) { this.direction = Direction.DOWN; }
            else if (number <= 0.75) { this.direction = Direction.LEFT; }
            else { this.direction = Direction.RIGHT; }
            this.actionCounterFrames = 0;
        }
    }
}
