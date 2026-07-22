package main;

import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {
    private final GamePanel gamePanel;

    public AssetSetter(GamePanel p) {
        this.gamePanel = p;
    }

    public void setObjects() {
        ObjectKey key = new ObjectKey();
        key.worldX = 23 * this.gamePanel.tileSize;
        key.worldY = 10 * this.gamePanel.tileSize;

        ObjectChest chest = new ObjectChest();
        chest.worldX = 10 * this.gamePanel.tileSize;
        chest.worldY = 11 * this.gamePanel.tileSize;

        ObjectDoor door = new ObjectDoor();
        door.worldX = 10 * this.gamePanel.tileSize;
        door.worldY = 14 * this.gamePanel.tileSize;


        this.gamePanel.objects.add(key);
        this.gamePanel.objects.add(chest);
        this.gamePanel.objects.add(door);
    }
}
