package object;

import enums.ObjectTyp;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectDoor extends GameObject{

    public ObjectDoor(GamePanel gamePanel, int worldColumn, int worldRow) {
        this.name = "Door";
        this.typ = ObjectTyp.DOOR;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        this.isSolid = true;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/door.png")));
            this.utilityTool.scaleImage(this.image, gamePanel.tileSize, gamePanel.tileSize);
        }
        catch (IOException _) {}
    }
}
