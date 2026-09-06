package main;

import entity.NPCOldMan;
import monster.GreenSlime;
import object.*;
import tileInteractive.InteractiveTile;
import tileInteractive.InteractiveTileDryTree;

public class AssetSetter {
    private final GamePanel gamePanel;

    public AssetSetter(GamePanel p) {
        this.gamePanel = p;
    }

    public void setPlayer() {
        this.gamePanel.entities.add(this.gamePanel.player);
    }

    public void setObjects() {
        ObjectKeySilver key1 = new ObjectKeySilver(this.gamePanel, 22, 22);
        ObjectKeySilver key2 = new ObjectKeySilver(this.gamePanel, 20, 25);
        ObjectKeySilver key3 = new ObjectKeySilver(this.gamePanel, 24, 25);
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

    public void setMonsters() {
        GreenSlime slime = new GreenSlime(this.gamePanel, 23, 38);
        GreenSlime slime1 = new GreenSlime(this.gamePanel, 23, 40);
        GreenSlime slime2 = new GreenSlime(this.gamePanel, 23, 43);
        GreenSlime slime3 = new GreenSlime(this.gamePanel, 23, 42);

        this.gamePanel.entities.add(slime);
        this.gamePanel.entities.add(slime1);
        this.gamePanel.entities.add(slime2);
        this.gamePanel.entities.add(slime3);
    }

    public void setInteractiveTiles() {
        InteractiveTile it1 = new InteractiveTileDryTree(this.gamePanel, 26, 15);
        InteractiveTile it2 = new InteractiveTileDryTree(this.gamePanel, 27, 15);
        InteractiveTile it3 = new InteractiveTileDryTree(this.gamePanel, 28, 15);
        InteractiveTile it4 = new InteractiveTileDryTree(this.gamePanel, 29, 15);
        InteractiveTile it5 = new InteractiveTileDryTree(this.gamePanel, 30, 15);
        InteractiveTile it6 = new InteractiveTileDryTree(this.gamePanel, 31, 15);
        InteractiveTile it7 = new InteractiveTileDryTree(this.gamePanel, 32, 15);
        InteractiveTile it8 = new InteractiveTileDryTree(this.gamePanel, 19, 23);
        InteractiveTile it9 = new InteractiveTileDryTree(this.gamePanel, 19, 24);
        InteractiveTile it10 = new InteractiveTileDryTree(this.gamePanel, 19, 25);

        this.gamePanel.entities.add(it1);
        this.gamePanel.entities.add(it2);
        this.gamePanel.entities.add(it3);
        this.gamePanel.entities.add(it4);
        this.gamePanel.entities.add(it5);
        this.gamePanel.entities.add(it6);
        this.gamePanel.entities.add(it7);
        this.gamePanel.entities.add(it8);
        this.gamePanel.entities.add(it9);
        this.gamePanel.entities.add(it10);
    }
}
