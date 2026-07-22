package object;

import enums.ObjectTyp;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends GameObject {

    public ObjectKey() {
        this.typ = ObjectTyp.Key;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
        }
        catch (IOException e) {}
    }
}
