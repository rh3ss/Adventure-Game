package monster;

import entity.Entity;
import enums.Direction;
import enums.EntityType;
import main.GamePanel;

import java.util.Random;

public class Monster extends Entity{
    public final Random random;

    public Monster(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel);
        this.random = new Random();

        this.entityType = EntityType.MONSTER;
        this.isSolid = true;
        this.activationRadius = gamePanel.tileSize * 3;
    }

    public void getImages() {}

    public void update() {
        super.update();

        // if player is to close to Monster it follows the players path
        if (!this.onTrackingPath && this.isPlayerInActivationRadius()) {
            if (random.nextInt(101) > 50) {
                this.onTrackingPath = true;
            }
        }
    }

    public void setAction() {
        if (this.onTrackingPath) {
            int destinationColumn = (this.gamePanel.player.worldX + this.gamePanel.player.solidArea.x) / this.gamePanel.tileSize;
            int destinationRow = (this.gamePanel.player.worldY + this.gamePanel.player.solidArea.y) / this.gamePanel.tileSize;
            this.searchDestinationPath(destinationColumn, destinationRow);
        }
        else {
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

    public void damageReaction() {
        this.actionCounterFrames = 0;
        // chase player after monster received damage
        this.onTrackingPath = true;
    }

    public void chooseObjectToDrop() {}
}
