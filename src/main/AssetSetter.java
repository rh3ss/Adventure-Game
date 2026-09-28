package main;

import entity.Entity;
import npc.*;
import monster.*;
import object.GameObject;
import object.armor.*;
import object.interactable.*;
import object.obstacle.Chest;
import object.obstacle.Door;
import object.pickup.*;
import object.potion.*;
import object.shield.*;
import object.weapon.*;
import tileFix.*;
import tileInteractive.*;

import java.util.ArrayList;
import java.util.List;

public class AssetSetter {
    private final GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        MapData world = new MapData();
        MapData hutIndoor = new MapData();
        MapData farmBarn = new MapData();
        this.gamePanel.maps.put(0, world);
        this.gamePanel.maps.put(1, hutIndoor);
        this.gamePanel.maps.put(2, farmBarn);
    }

    public void setAllAssetSetter() {
        this.setPlayer();
        this.setObjects();
        this.setNPCs();
        this.setMonsters();
        this.setInteractiveTiles();
        this.setFixTiles();
    }

    public void setPlayer() {
        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.remove(this.gamePanel.player);
        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(this.gamePanel.player);
    }

    public void setObjects() {
        ArrayList<Entity> objects = new ArrayList<>(List.of(
                new KeySilver(this.gamePanel, 27, 25),
                new KeyGold(this.gamePanel, 28, 25),

                new Coin(this.gamePanel, 25, 23),
                new CoinPile(this.gamePanel, 26, 23),
                new CoinBag(this.gamePanel, 27, 23),

                new Heart(this.gamePanel, 25, 25),
                new ManaCrystal(this.gamePanel, 26, 25),

                new Door(this.gamePanel, 19, 14),
                new Door(this.gamePanel, 25, 14),

                new Chest(this.gamePanel, 16, 17, new ArrayList<>(List.of(
                    new PotionExperience(this.gamePanel, 21, 25),
                    new PotionHeal(this.gamePanel, 21, 26),
                    new PotionSpeed(this.gamePanel, 21, 27),
                    new PotionStrength(this.gamePanel, 21, 28),
                    new AxeIron(this.gamePanel, 22, 25),
                    new SwordIron(this.gamePanel, 22, 26),
                    new AxeGold(this.gamePanel, 22, 27),
                    new SwordGold(this.gamePanel, 22, 28)
                ))),

                new PotionExperience(this.gamePanel, 21, 25),
                new PotionHeal(this.gamePanel, 21, 26),
                new PotionSpeed(this.gamePanel, 21, 27),
                new PotionStrength(this.gamePanel, 21, 28),

                new AxeIron(this.gamePanel, 22, 25),
                new SwordIron(this.gamePanel, 22, 26),
                new AxeGold(this.gamePanel, 22, 27),
                new SwordGold(this.gamePanel, 22, 28),

                new ShieldWood(this.gamePanel, 23, 25),
                new ShieldBlue(this.gamePanel, 23, 26),

                new ArmorIron(this.gamePanel, 23, 27),
                new ArmorGold(this.gamePanel, 23, 28)
        ));

        this.gamePanel.maps.get(0).entities.addAll(objects);
    }

    public void setNPCs() {
        ArrayList<Entity> npcs = new ArrayList<>(List.of(
                new OldMan(this.gamePanel, 18, 23),
                new Trader(this.gamePanel, 21, 18),
                new Blacksmith(this.gamePanel, 23, 18),
                new Farmer(this.gamePanel, 13, 14)
        ));

        this.gamePanel.maps.get(0).entities.addAll(npcs);
    }

    public void setMonsters() {
        ArrayList<Entity> monsters = new ArrayList<>(List.of(
                new MonsterSlimeGreen(this.gamePanel, 23, 38),
                new MonsterSlimeGreen(this.gamePanel, 23, 40),
                new MonsterSlimeGreen(this.gamePanel, 23, 43),
                new MonsterSlimeGreen(this.gamePanel, 23, 42),

                new MonsterSlimeRed(this.gamePanel, 21, 38),
                new MonsterSlimeRed(this.gamePanel, 21, 40),
                new MonsterSlimeRed(this.gamePanel, 21, 43),
                new MonsterSlimeRed(this.gamePanel, 21, 42)
        ));

        this.gamePanel.maps.get(0).entities.addAll(monsters);
    }

    public void setInteractiveTiles() {
        ArrayList<Entity> interactiveTiles = new ArrayList<>(List.of(
                new DryTree(this.gamePanel, 27, 15),
                new DryTree(this.gamePanel, 28, 15),
                new DryTree(this.gamePanel, 29, 15),
                new DryTree(this.gamePanel, 30, 15),
                new DryTree(this.gamePanel, 31, 15),
                new DryTree(this.gamePanel, 32, 15),

                new Bush(this.gamePanel, "bush_2", 19, 23),
                new Bush(this.gamePanel, "bush_1", 19, 24),
                new Bush(this.gamePanel, "bush_2", 19, 25),

                new Wheat(this.gamePanel, 14, 12),
                new Wheat(this.gamePanel, 15, 12),
                new Wheat(this.gamePanel, 16, 12),
                new Wheat(this.gamePanel, 17, 12),
                new Wheat(this.gamePanel, 18, 12),

                new Wheat(this.gamePanel, 14, 13),
                new Wheat(this.gamePanel, 15, 13),
                new Wheat(this.gamePanel, 16, 13),
                new Wheat(this.gamePanel, 17, 13),
                new Wheat(this.gamePanel, 18, 13),

                new Wheat(this.gamePanel, 14, 14),
                new Wheat(this.gamePanel, 15, 14),
                new Wheat(this.gamePanel, 16, 14),
                new Wheat(this.gamePanel, 17, 14),
                new Wheat(this.gamePanel, 18, 14),

                new Wheat(this.gamePanel, 14, 15),
                new Wheat(this.gamePanel, 15, 15),
                new Wheat(this.gamePanel, 16, 15),
                new Wheat(this.gamePanel, 17, 15),
                new Wheat(this.gamePanel, 18, 15)
        ));

        this.gamePanel.maps.get(0).entities.addAll(interactiveTiles);
    }

    public void setFixTiles() {
        ArrayList<Entity> fixTiles = new ArrayList<>(List.of(
                new FarmBarn(this.gamePanel, 8, 11),

                new BigTree(this.gamePanel, 17, 21),
                new BigTree(this.gamePanel, 18, 21),
                new BigTree(this.gamePanel, 19, 21),
                new BigTree(this.gamePanel, 20, 21)
        ));
        this.gamePanel.maps.get(0).entities.addAll(fixTiles);
    }
}
