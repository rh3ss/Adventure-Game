package object;

import enums.ObjectTyp;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends GameObject{
    private GamePanel gamePanel;

    public ObjectChest(GamePanel p) {
        this.gamePanel = p;
        this.typ = ObjectTyp.Chest;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            this.utilityTool.scaleImage(this.image, this.gamePanel.tileSize, this.gamePanel.tileSize);
        }
        catch (IOException e) {}
    }
}
