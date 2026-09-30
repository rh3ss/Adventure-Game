package object.light;

import enums.ObjectType;
import main.GamePanel;

public class Torch extends Light {

    public Torch(GamePanel gamePanel, int worldColumn, int worldRow) {
        super(gamePanel, worldColumn, worldRow);

        this.objectType = ObjectType.TORCH;
        this.down1 = this.setupEntityImage("/res/objects/torch.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        this.lightRadius = 200;
        this.objectCoinValue = 100;
        this.objectName = "Torch";
        this.objectDescription = "[" + this.objectName + "]\nIlluminates your \nsurroundings";
    }
}
