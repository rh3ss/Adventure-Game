package entity;


import enums.Direction;
import main.GamePanel;

import java.util.Random;

public class NPCOldMan extends Entity {

    public NPCOldMan(GamePanel p, int worldColumn, int worldRow) {
        super(p);
        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;
        this.velocity = 1;

        this.getNPCOldManImages();
    }

    private void getNPCOldManImages() {
        this.up1 = this.setupEntityImage("/res/npc/oldman_up_1.png");
        this.up2 = this.setupEntityImage("/res/npc/oldman_up_2.png");
        this.down1 = this.setupEntityImage("/res/npc/oldman_down_1.png");
        this.down2 = this.setupEntityImage("/res/npc/oldman_down_2.png");
        this.left1 = this.setupEntityImage("/res/npc/oldman_left_1.png");
        this.left2 = this.setupEntityImage("/res/npc/oldman_left_2.png");
        this.right1 = this.setupEntityImage("/res/npc/oldman_right_1.png");
        this.right2 = this.setupEntityImage("/res/npc/oldman_right_2.png");
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
