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
        ObjectKey key = new ObjectKey(this.gamePanel, 22, 15);
        ObjectChest chest = new ObjectChest(this.gamePanel, 19, 12);
        ObjectDoor door = new ObjectDoor(this.gamePanel, 23, 23);
        ObjectBoots boots = new ObjectBoots(this.gamePanel, 22, 27);

        this.gamePanel.entities.add(key);
        this.gamePanel.entities.add(chest);
        this.gamePanel.entities.add(door);
        this.gamePanel.entities.add(boots);
    }

    public void setPlayer() {
        this.gamePanel.entities.add(this.gamePanel.player);
    }

    public void setNPCs() {
        NPCOldMan oldMan1 = new NPCOldMan(this.gamePanel, 21, 21);
        this.gamePanel.entities.add(oldMan1);
//        NPCOldMan oldMan2 = new NPCOldMan(this.gamePanel, 28, 24);
//        this.gamePanel.entities.add(oldMan2);
//        NPCOldMan oldMan3 = new NPCOldMan(this.gamePanel, 22, 15);
//        this.gamePanel.entities.add(oldMan3);

    }
}
