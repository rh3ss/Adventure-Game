package main;

import entity.Entity;
import npc.OldMan;
import monster.Monster;
import monster.MonsterSlimeGreen;
import monster.MonsterSlimeRed;
import npc.Trader;
import object.armor.Armor;
import object.armor.ArmorGold;
import object.armor.ArmorIron;
import object.interactable.KeyGold;
import object.interactable.KeySilver;
import object.interactable.Door;
import object.pickup.PickUp;
import object.pickup.PickUpHeart;
import object.pickup.PickUpManaCrystal;
import object.pickup.PickUpCoin;
import object.potion.*;
import object.shield.Shield;
import object.shield.ShieldBlue;
import object.shield.ShieldWood;
import object.weapon.*;
import tileInteractive.InteractiveTile;
import tileInteractive.InteractiveTileBush;
import tileInteractive.InteractiveTileDryTree;

import java.util.ArrayList;
import java.util.List;

public class AssetSetter {
    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        MapData world = new MapData();
        MapData hutIndoor = new MapData();
        this.gamePanel.maps.put(0, world);
        this.gamePanel.maps.put(1, hutIndoor);
    }

    public void setPlayer() {
        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.remove(this.gamePanel.player);
        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(this.gamePanel.player);
    }

    public void setObjects() {
        KeySilver key1 = new KeySilver(this.gamePanel, 27, 25);
        KeyGold key2 = new KeyGold(this.gamePanel, 28, 25);
        PickUp coin1 = new PickUpCoin(this.gamePanel, 22, 23);
        PickUp coin2 = new PickUpCoin(this.gamePanel, 23, 23);
        PickUp heart1 = new PickUpHeart(this.gamePanel, 25, 23);
        PickUp mana1 = new PickUpManaCrystal(this.gamePanel, 26, 23);
        Door door1 = new Door(this.gamePanel, 22, 42);
        Potion potion1 = new PotionExperience(this.gamePanel, 21, 25);
        Potion potion2 = new PotionHeal(this.gamePanel, 21, 26);
        Potion potion3 = new PotionSpeed(this.gamePanel, 21, 27);
        Potion potion4 = new PotionStrength(this.gamePanel, 21, 28);
        Weapon axeiron1 = new AxeIron(this.gamePanel, 22, 25);
        Weapon swordIron1 = new SwordIron(this.gamePanel, 22, 26);
        Weapon axegold1 = new AxeGold(this.gamePanel, 22, 27);
        Weapon swordGold1 = new SwordGold(this.gamePanel, 22, 28);
        Shield shieldWood1 = new ShieldWood(this.gamePanel, 23, 25);
        Shield shieldBlue1 = new ShieldBlue(this.gamePanel, 23, 26);
        Armor armorIron1 = new ArmorIron(this.gamePanel, 23, 27);
        Armor armorGold1 = new ArmorGold(this.gamePanel, 23, 28);

        // objects for world map 0
        ArrayList<Entity> objects = new ArrayList<>(List.of(
                key1, key2,
                coin1, coin2,
                heart1, mana1,
                door1,
                potion1, potion2, potion3, potion4,
                axeiron1, swordIron1, axegold1, swordGold1,
                shieldWood1, shieldBlue1,
                armorIron1, armorGold1
        ));
        this.gamePanel.maps.get(0).entities.addAll(objects);

//        // objects for world map 1
//        objects = new ArrayList<>(List.of(
//                key1
//        ));
//        this.gamePanel.maps.get(1).entities.addAll(objects);
    }

    public void setNPCs() {
        OldMan oldMan = new OldMan(this.gamePanel, 21, 21);

        ArrayList<Entity> npcs = new ArrayList<>(List.of(
                oldMan
        ));
        this.gamePanel.maps.get(0).entities.addAll(npcs);



        Trader trader = new Trader(this.gamePanel, 22, 20);
        Potion p = new PotionHeal(this.gamePanel, 22, 20);
        p.worldX += 20;
        ArrayList<Entity> npcs2 = new ArrayList<>(List.of(
                p, trader
        ));
        this.gamePanel.maps.get(0).entities.addAll(npcs2);
    }

    public void setMonsters() {
        Monster slime = new MonsterSlimeGreen(this.gamePanel, 23, 38);
        Monster slime1 = new MonsterSlimeGreen(this.gamePanel, 23, 40);
        Monster slime2 = new MonsterSlimeGreen(this.gamePanel, 23, 43);
        Monster slime3 = new MonsterSlimeGreen(this.gamePanel, 23, 42);
        Monster monsterSlimeRed1 = new MonsterSlimeRed(this.gamePanel, 21, 38);
        Monster monsterSlimeRed2 = new MonsterSlimeRed(this.gamePanel, 21, 40);
        Monster monsterSlimeRed3 = new MonsterSlimeRed(this.gamePanel, 21, 43);
        Monster monsterSlimeRed4 = new MonsterSlimeRed(this.gamePanel, 21, 42);

        ArrayList<Entity> monster = new ArrayList<>(List.of(
                slime, slime1, slime2, slime3,
                monsterSlimeRed1, monsterSlimeRed2, monsterSlimeRed3, monsterSlimeRed4
        ));
        this.gamePanel.maps.get(0).entities.addAll(monster);
    }

    public void setInteractiveTiles() {
        InteractiveTile it1 = new InteractiveTileDryTree(this.gamePanel, 26, 15);
        InteractiveTile it2 = new InteractiveTileDryTree(this.gamePanel, 27, 15);
        InteractiveTile it3 = new InteractiveTileDryTree(this.gamePanel, 28, 15);
        InteractiveTile it4 = new InteractiveTileDryTree(this.gamePanel, 29, 15);
        InteractiveTile it5 = new InteractiveTileDryTree(this.gamePanel, 30, 15);
        InteractiveTile it6 = new InteractiveTileDryTree(this.gamePanel, 31, 15);
        InteractiveTile it7 = new InteractiveTileDryTree(this.gamePanel, 32, 15);
        InteractiveTile it8 = new InteractiveTileBush(this.gamePanel, "bush_2", 19, 23);
        InteractiveTile it9 = new InteractiveTileBush(this.gamePanel, "bush_1", 19, 24);
        InteractiveTile it10 = new InteractiveTileBush(this.gamePanel, "bush_2", 19, 25);

        ArrayList<Entity> interactiveTiles = new ArrayList<>(List.of(
                it1, it2, it3, it4, it5, it6, it7, it8, it9, it10
        ));
        this.gamePanel.maps.get(0).entities.addAll(interactiveTiles);
    }
}
