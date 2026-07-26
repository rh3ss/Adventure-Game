package object;

import enums.ObjectTyp;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;

public class ObjectBoots extends GameObject{

    public ObjectBoots(GamePanel gamePanel, int worldColumn, int worldRow) {
        this.name = "SpeedBoots";
        this.typ = ObjectTyp.BOOTS;
        this.worldX = worldColumn * gamePanel.tileSize;
        this.worldY = worldRow * gamePanel.tileSize;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/boots.png")));
            this.utilityTool.scaleImage(this.image, gamePanel.tileSize, gamePanel.tileSize);
        }
        catch (IOException _) {}
    }
}
