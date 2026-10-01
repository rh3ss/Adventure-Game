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
import portal.Portal;
import tileFix.*;
import tileInteractive.*;

import java.util.ArrayList;
import java.util.List;

public class AssetSetter {
    private final GamePanel gamePanel;
    public Portal portalLeaveSpawnIsland, portalAccessSpawnIsland;
    public Portal portalLeaveSouthIsland, portalAccessSouthIsland;

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
        this.setPortals();
        this.setInteractiveTiles();
        this.setFixTiles();
    }

    public void setPlayer() {
        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.remove(this.gamePanel.player);
        this.gamePanel.maps.get(this.gamePanel.currentMapNumber).entities.add(this.gamePanel.player);
    }

    public void setObjects() {
        ArrayList<Entity> objects = new ArrayList<>(List.of(
                // spawn island
                new Chest(this.gamePanel, 9, 6, new ArrayList<>(List.of(
                        new Coin(this.gamePanel, 24, 17),
                        new Coin(this.gamePanel, 24, 17),
                        new CoinPile(this.gamePanel, 24, 18),
                        new CoinPile(this.gamePanel, 24, 18),
                        new CoinBag(this.gamePanel, 24, 19),
                        new CoinBag(this.gamePanel, 24, 19)
                ))),

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

    public void setPortals() {
        this.portalLeaveSpawnIsland = new Portal(this.gamePanel, 8, 11);
        this.portalAccessSpawnIsland = new Portal(this.gamePanel, 16, 12);
        this.portalLeaveSouthIsland = new Portal(this.gamePanel, 40, 31);
        this.portalAccessSouthIsland = new Portal(this.gamePanel, 38, 24);

        ArrayList<Portal> portals = new ArrayList<>(List.of(
                this.portalLeaveSpawnIsland,
                this.portalAccessSpawnIsland,
                this.portalLeaveSouthIsland,
                this.portalAccessSouthIsland
        ));

        this.gamePanel.maps.get(0).entities.addAll(portals);
    }

    public void setInteractiveTiles() {
        ArrayList<Entity> interactiveTiles = new ArrayList<>(List.of(
                // spawn island
                new SmallTree(this.gamePanel, 7, 6),
                new SmallTree(this.gamePanel, 9, 11),

                // main island
                new Bush(this.gamePanel, "bush_2", 5, 7),
                new Bush(this.gamePanel, "bush_1", 11, 9),
                new Bush(this.gamePanel, "bush_2", 9, 5),
                new SmallTree(this.gamePanel, 21, 7),
                new SmallTree(this.gamePanel, 27, 6),
                new SmallTree(this.gamePanel, 31, 8),
                new SmallTree(this.gamePanel, 36, 10),
                new Bush(this.gamePanel, "bush_1", 20, 9),
                new Bush(this.gamePanel, "bush_2", 24, 7),
                new Bush(this.gamePanel, "bush_1", 29, 9),
                new Bush(this.gamePanel, "bush_2", 33, 7),
                new Bush(this.gamePanel, "bush_1", 36, 12),
                new SmallTree(this.gamePanel, 17, 13),
                new SmallTree(this.gamePanel, 15, 17),
                new SmallTree(this.gamePanel, 13, 21),
                new SmallTree(this.gamePanel, 10, 25),
                new SmallTree(this.gamePanel, 9, 30),
                new SmallTree(this.gamePanel, 10, 34),
                new Bush(this.gamePanel, "bush_2", 17, 15),
                new Bush(this.gamePanel, "bush_1", 14, 19),
                new Bush(this.gamePanel, "bush_2", 11, 23),
                new Bush(this.gamePanel, "bush_1", 9, 28),
                new Bush(this.gamePanel, "bush_2", 11, 32),
                new Bush(this.gamePanel, "bush_1", 14, 35),
                new SmallTree(this.gamePanel, 36, 15),
                new SmallTree(this.gamePanel, 34, 19),
                new SmallTree(this.gamePanel, 34, 24),
                new SmallTree(this.gamePanel, 37, 28),
                new SmallTree(this.gamePanel, 38, 32),
                new SmallTree(this.gamePanel, 37, 36),
                new Bush(this.gamePanel, "bush_1", 36, 17),
                new Bush(this.gamePanel, "bush_2", 33, 21),
                new Bush(this.gamePanel, "bush_1", 35, 26),
                new Bush(this.gamePanel, "bush_2", 38, 30),
                new Bush(this.gamePanel, "bush_1", 36, 34),
                new Bush(this.gamePanel, "bush_2", 34, 38),
                new SmallTree(this.gamePanel, 16, 39),
                new SmallTree(this.gamePanel, 21, 41),
                new SmallTree(this.gamePanel, 26, 40),
                new SmallTree(this.gamePanel, 30, 39),
                new Bush(this.gamePanel, "bush_1", 18, 40),
                new Bush(this.gamePanel, "bush_2", 23, 41),
                new Bush(this.gamePanel, "bush_1", 28, 39),
                new Bush(this.gamePanel, "bush_2", 32, 38),
                new SmallTree(this.gamePanel, 22, 18),
                new SmallTree(this.gamePanel, 29, 16),
                new SmallTree(this.gamePanel, 18, 28),
                new SmallTree(this.gamePanel, 29, 29),
                new SmallTree(this.gamePanel, 24, 34),
                new Bush(this.gamePanel, "bush_2", 21, 20),
                new Bush(this.gamePanel, "bush_1", 27, 18),
                new Bush(this.gamePanel, "bush_2", 19, 27),
                new Bush(this.gamePanel, "bush_1", 31, 27),
                new Bush(this.gamePanel, "bush_2", 26, 33)

                // south island
        ));
        this.gamePanel.maps.get(0).entities.addAll(interactiveTiles);
    }

    public void setFixTiles() {
        ArrayList<TileFix> fixTiles = new ArrayList<>(List.of(
                // spawn island
                new TreeSquares(this.gamePanel, 12, 6),
                new TreeSquares(this.gamePanel, 10, 10),
                new TreeSquares(this.gamePanel, 6, 11),
                new TreeMiddle(this.gamePanel, 8, 5),
                new TreeMiddle(this.gamePanel, 11, 8),
                new TreePine(this.gamePanel, 6, 7),

                // main island
                new TreePine(this.gamePanel, 20, 8),
                new TreeMiddle(this.gamePanel, 23, 7),
                new TreeSquares(this.gamePanel, 26, 7),
                new TreePine(this.gamePanel, 30, 8),
                new TreeMiddle(this.gamePanel, 33, 9),
                new TreeSquares(this.gamePanel, 36, 11),
                new TreeBig(this.gamePanel, 24, 11),
                new TreeBig(this.gamePanel, 31, 12),
                new TreePine(this.gamePanel, 17, 15),
                new TreeSquares(this.gamePanel, 15, 18),
                new TreeMiddle(this.gamePanel, 13, 21),
                new TreeBig(this.gamePanel, 18, 20),
                new TreePine(this.gamePanel, 11, 24),
                new TreeMiddle(this.gamePanel, 9, 27),
                new TreeSquares(this.gamePanel, 9, 31),
                new TreePine(this.gamePanel, 11, 35),
                new TreeMiddle(this.gamePanel, 14, 38),
                new TreeBig(this.gamePanel, 14, 29),
                new TreeBig(this.gamePanel, 16, 35),
                new TreeMiddle(this.gamePanel, 36, 15),
                new TreePine(this.gamePanel, 34, 18),
                new TreeSquares(this.gamePanel, 35, 21),
                new TreeBig(this.gamePanel, 31, 20),
                new TreePine(this.gamePanel, 35, 25),
                new TreeMiddle(this.gamePanel, 38, 29),
                new TreeSquares(this.gamePanel, 37, 33),
                new TreePine(this.gamePanel, 35, 37),
                new TreeBig(this.gamePanel, 31, 29),
                new TreeBig(this.gamePanel, 32, 35),
                new TreeSquares(this.gamePanel, 17, 40),
                new TreePine(this.gamePanel, 21, 41),
                new TreeMiddle(this.gamePanel, 25, 40),
                new TreeSquares(this.gamePanel, 29, 39),
                new TreeBig(this.gamePanel, 23, 39),
                new TreeMiddle(this.gamePanel, 20, 17),
                new TreeSquares(this.gamePanel, 23, 19),
                new TreePine(this.gamePanel, 28, 17),
                new TreeSquares(this.gamePanel, 19, 25),
                new TreeMiddle(this.gamePanel, 23, 27),
                new TreePine(this.gamePanel, 29, 25),
                new TreeMiddle(this.gamePanel, 20, 33),
                new TreeSquares(this.gamePanel, 27, 33)

                // south island
        ));
        this.gamePanel.maps.get(0).entities.addAll(fixTiles);
    }
}
