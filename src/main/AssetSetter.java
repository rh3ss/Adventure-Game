package main;

import entity.NPCOldMan;
import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {
    private final GamePanel gamePanel;

    public AssetSetter(GamePanel p) {
        this.gamePanel = p;
    }

    public void setObjects() {
        ObjectKey key = new ObjectKey(this.gamePanel, 23, 7);
        ObjectChest chest = new ObjectChest(this.gamePanel, 10, 9);
        ObjectDoor door = new ObjectDoor(this.gamePanel, 10, 12);
        ObjectBoots boots = new ObjectBoots(this.gamePanel, 21, 23);

        this.gamePanel.objects.add(key);
        this.gamePanel.objects.add(chest);
        this.gamePanel.objects.add(door);
        this.gamePanel.objects.add(boots);
    }

    public void setNPCs() {
        NPCOldMan oldMan = new NPCOldMan(this.gamePanel, 21, 21);
        this.gamePanel.npcs.add(oldMan);
    }
}
