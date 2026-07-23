package object;

import enums.ObjectTyp;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends GameObject {
    private GamePanel gamePanel;

    public ObjectKey(GamePanel p) {
        this.gamePanel = p;
        this.typ = ObjectTyp.Key;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
            this.utilityTool.scaleImage(this.image, this.gamePanel.tileSize, this.gamePanel.tileSize);
        }
        catch (IOException e) {}
    }
}
