package main;

import entity.Entity;
import npc.*;
import monster.*;
import object.armor.*;
import object.consumable.*;
import object.interactable.*;
import object.light.*;
import object.obstacle.*;
import object.pickup.*;
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
                new KeySilver(this.gamePanel, 24, 15),
                new KeyGold(this.gamePanel, 24, 16),

                new Coin(this.gamePanel, 24, 17),
                new CoinPile(this.gamePanel, 24, 18),
                new CoinBag(this.gamePanel, 24, 19),

                new Heart(this.gamePanel, 21, 20),
                new ManaCrystal(this.gamePanel, 22, 20),
                new Torch(this.gamePanel, 23, 20),

//                new Door(this.gamePanel, 19, 20),
//                new Door(this.gamePanel, 25, 20),

                new Chest(this.gamePanel, 15, 17, new ArrayList<>(List.of(
                    new PotionExperience(this.gamePanel, 21, 25),
                    new PotionHeal(this.gamePanel, 21, 26),
                    new PotionSpeed(this.gamePanel, 21, 27),
                    new PotionStrength(this.gamePanel, 21, 28),
                    new AxeIron(this.gamePanel, 22, 25),
                    new SwordIron(this.gamePanel, 22, 26),
                    new AxeGold(this.gamePanel, 22, 27),
                    new SwordGold(this.gamePanel, 22, 28)
                ))),

                new AxeIron(this.gamePanel, 21, 15),
                new SwordIron(this.gamePanel, 21, 16),
                new AxeGold(this.gamePanel, 21, 17),
                new SwordGold(this.gamePanel, 21, 18),

                new ShieldWood(this.gamePanel, 22, 15),
                new ShieldBlue(this.gamePanel, 22, 16),

                new ArmorIron(this.gamePanel, 22, 17),
                new ArmorGold(this.gamePanel, 22, 18),

                new PotionExperience(this.gamePanel, 23, 15),
                new PotionHeal(this.gamePanel, 23, 16),
                new PotionSpeed(this.gamePanel, 23, 17),
                new PotionStrength(this.gamePanel, 23, 18)
        ));

        this.gamePanel.maps.get(0).entities.addAll(objects);
    }

    public void setNPCs() {
        ArrayList<Entity> npcs = new ArrayList<>(List.of(
                new OldMan(this.gamePanel, 10, 18),
                new Trader(this.gamePanel, 11, 18),
                new Blacksmith(this.gamePanel, 12, 18),
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
                new SmallTree(this.gamePanel, 17, 16),
                new SmallTree(this.gamePanel, 18, 16),
                new SmallTree(this.gamePanel, 19, 16),
                new SmallTree(this.gamePanel, 20, 16),

                new Bush(this.gamePanel, "bush_2", 15, 20),
                new Bush(this.gamePanel, "bush_1", 15, 21),
                new Bush(this.gamePanel, "bush_2", 15, 22),

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
        ArrayList<TileFix> fixTiles = new ArrayList<>(List.of(
                new FarmBarn(this.gamePanel, 8, 11),

                new TreeMiddle(this.gamePanel, 17, 18),
                new TreeMiddle(this.gamePanel, 18, 18),
                new TreeMiddle(this.gamePanel, 19, 18),
                new TreeMiddle(this.gamePanel, 20, 18),

                new TreeSquares(this.gamePanel, 17, 20),
                new TreeSquares(this.gamePanel, 18, 20),
                new TreeSquares(this.gamePanel, 19, 20),
                new TreeSquares(this.gamePanel, 20, 20),

                new TreePine(this.gamePanel, 17, 23),
                new TreePine(this.gamePanel, 18, 23),
                new TreePine(this.gamePanel, 19, 23),
                new TreePine(this.gamePanel, 20, 23),


                new TreeBig(this.gamePanel, 13, 20),
                new TreeBig(this.gamePanel, 13, 23)
        ));
        this.gamePanel.maps.get(0).entities.addAll(fixTiles);
    }
}
