package object;

import enums.ObjectTyp;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends GameObject{

    public ObjectChest() {
        this.typ = ObjectTyp.Chest;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
        }
        catch (IOException e) {}
    }
}
