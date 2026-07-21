package entity;

import enums.Direction;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int velocity;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public Direction direction;

    public int animationCounter = 0;
    public int animationFrame = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;
}
