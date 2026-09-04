package main;

import entity.NPCOldMan;
import monster.GreenSlime;
import object.*;

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
        ObjectCoin coin1 = new ObjectCoin(this.gamePanel, 20, 24);
        ObjectCoin coin2 = new ObjectCoin(this.gamePanel, 24, 24);
        ObjectHeart heart1 = new ObjectHeart(this.gamePanel, 22, 24);
        ObjectManaCrystal mana1 = new ObjectManaCrystal(this.gamePanel, 22, 26);
        ObjectDoor door1 = new ObjectDoor(this.gamePanel, 22, 42);
        ObjectAxe axe1 = new ObjectAxe(this.gamePanel, 28, 24);
        ObjectShieldBlue shieldBlue1 = new ObjectShieldBlue(this.gamePanel, 16, 24);
        ObjectPotion potion1 = new ObjectPotion(this.gamePanel, 22, 28);

        this.gamePanel.entities.add(key1);
        this.gamePanel.entities.add(key2);
        this.gamePanel.entities.add(key3);
        this.gamePanel.entities.add(coin1);
        this.gamePanel.entities.add(coin2);
        this.gamePanel.entities.add(heart1);
        this.gamePanel.entities.add(mana1);
        this.gamePanel.entities.add(door1);
        this.gamePanel.entities.add(axe1);
        this.gamePanel.entities.add(shieldBlue1);
        this.gamePanel.entities.add(potion1);
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
