package portal;

import entity.Entity;
import main.GamePanel;

public class Portal extends Entity {

    public Portal(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);

        this.worldX = this.gamePanel.tileSize * worldColumn;
        this.worldY = this.gamePanel.tileSize * worldRow;

        this.getImages();
    }

    public void getImages() {
        this.up1 = this.setupEntityImage("/res/portal/portal_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.up2 = this.setupEntityImage("/res/portal/portal_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down1 = this.setupEntityImage("/res/portal/portal_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.down2 = this.setupEntityImage("/res/portal/portal_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left1 = this.setupEntityImage("/res/portal/portal_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.left2 = this.setupEntityImage("/res/portal/portal_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right1 = this.setupEntityImage("/res/portal/portal_down_1.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.right2 = this.setupEntityImage("/res/portal/portal_down_2.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
    }
}
