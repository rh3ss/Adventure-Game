package main;

import entity.NPCOldMan;
import monster.GreenSlime;
import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {
    private final GamePanel gamePanel;

    public AssetSetter(GamePanel p) {
        this.gamePanel = p;
    }

    public void setPlayer() {
        this.gamePanel.entities.add(this.gamePanel.player);
    }

    public void setObjects() {
        ObjectKey key1 = new ObjectKey(this.gamePanel, 22, 22);
        ObjectKey key2 = new ObjectKey(this.gamePanel, 20, 25);
        ObjectKey key3 = new ObjectKey(this.gamePanel, 24, 25);
        ObjectDoor door1 = new ObjectDoor(this.gamePanel, 22, 42);

        this.gamePanel.entities.add(key1);
        this.gamePanel.entities.add(key2);
        this.gamePanel.entities.add(key3);
        this.gamePanel.entities.add(door1);
    }

    public void setNPCs() {
        NPCOldMan oldMan = new NPCOldMan(this.gamePanel, 21, 21);
        this.gamePanel.entities.add(oldMan);
    }

    public void setMonster() {
        GreenSlime slime = new GreenSlime(this.gamePanel, 23, 38);
        this.gamePanel.entities.add(slime);
        GreenSlime slime1 = new GreenSlime(this.gamePanel, 23, 40);
        this.gamePanel.entities.add(slime1);
        GreenSlime slime2 = new GreenSlime(this.gamePanel, 23, 43);
        this.gamePanel.entities.add(slime2);
        GreenSlime slime3 = new GreenSlime(this.gamePanel, 23, 42);
        this.gamePanel.entities.add(slime3);
    }
}
