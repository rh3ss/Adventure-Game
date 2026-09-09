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
    }

    public void getImages() {}

    public void setAction() {
        this.actionCounterFrames++;
        if (this.actionCounterFrames > 120) {
            double number = this.random.nextDouble();

            if (number <= 0.25) { this.direction = Direction.UP; }
            else if (number <= 0.50) { this.direction = Direction.DOWN; }
            else if (number <= 0.75) { this.direction = Direction.LEFT; }
            else { this.direction = Direction.RIGHT; }
            this.actionCounterFrames = 0;
        }
    }

    public void damageReaction() {
        this.actionCounterFrames = 0;
        this.direction = this.gamePanel.player.direction;
    }

    public void chooseObjectToDrop() {}
}
