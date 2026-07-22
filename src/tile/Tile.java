package tile;

import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    public boolean isSolid = false;

    public Tile(BufferedImage img, boolean solid) {
        this.image = img;
        this.isSolid = solid;
    }
}
