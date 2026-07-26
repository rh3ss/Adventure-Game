package object;

import enums.ObjectTyp;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectKey extends GameObject {

    public ObjectKey(GamePanel gamePanel, int worldColumn, int worldRow) {
        this.name = "DoorKey";
        this.typ = ObjectTyp.KEY;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/key.png")));
            this.utilityTool.scaleImage(this.image, gamePanel.tileSize, gamePanel.tileSize);
        }
        catch (IOException _) {}
    }
}
