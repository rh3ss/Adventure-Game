package object;

import enums.ObjectTyp;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends GameObject{
    private GamePanel gamePanel;

    public ObjectDoor(GamePanel p) {
        this.gamePanel = p;
        this.typ = ObjectTyp.Door;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            this.utilityTool.scaleImage(this.image, this.gamePanel.tileSize, this.gamePanel.tileSize);
        }
        catch (IOException e) {}
        this.isSolid = true;
    }
}
