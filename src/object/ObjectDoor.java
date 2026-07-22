package object;

import enums.ObjectTyp;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends GameObject{

    public ObjectDoor() {
        this.typ = ObjectTyp.Door;
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
        }
        catch (IOException e) {}
        this.isSolid = true;
    }
}
