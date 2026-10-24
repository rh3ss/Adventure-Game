package main;

import entity.Entity;
import monster.MonsterSlimeGreen;
import monster.MonsterSlimeRed;
import npc.*;
import object.GameObject;
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

        MapData world = new MapData(gamePanel, 50, 50);
        MapData farmBarn = new MapData(gamePanel, 7, 7);
        MapData hutIndoor = new MapData(gamePanel, 10, 10);
        this.gamePanel.maps.put(0, world);
        this.gamePanel.maps.put(1, farmBarn);
        this.gamePanel.maps.put(2, hutIndoor);
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
        ArrayList<GameObject> objects = new ArrayList<>(List.of(
                // north island
                new ChestIron(this.gamePanel, 9, 6, new ArrayList<>(List.of(
                        new Coin(this.gamePanel, -1, -1),
                        new Coin(this.gamePanel, -1, -1),
                        new CoinPile(this.gamePanel, -1, -1),
                        new CoinPile(this.gamePanel, -1, -1),
                        new CoinBag(this.gamePanel, -1, -1),
                        new CoinBag(this.gamePanel, -1, -1)
                ))),
                new AxeIron(this.gamePanel, 7, 7),
                new PotionStrength(this.gamePanel, 10, 9),
                new ManaCrystal(this.gamePanel, 12, 7),

                // main island
                new CoinBag(this.gamePanel, 18, 17),
                new CoinPile(this.gamePanel, 21, 17),
                new PotionSpeed(this.gamePanel, 19, 16),
                new PotionHeal(this.gamePanel, 29, 4),
                new ManaCrystal(this.gamePanel, 31, 8),
                new PotionStrength(this.gamePanel, 32, 24),
                new ChestIron(this.gamePanel, 37, 11, new ArrayList<>(List.of(
                        new KeySilver(this.gamePanel, -1, -1)
                ))),
                new ShieldBlue(this.gamePanel, 11, 18),
                new PotionExperience(this.gamePanel, 12, 20),
                new PotionHeal(this.gamePanel, 37, 13),
                new SwordGold(this.gamePanel, 34, 21),

                // around trader
                new CoinPile(this.gamePanel, 20, 24),
                new CoinBag(this.gamePanel, 21, 24),
                new PotionHeal(this.gamePanel, 22, 27),
                new PotionExperience(this.gamePanel, 23, 26),
                new CoinBag(this.gamePanel, 18, 27),
                new ManaCrystal(this.gamePanel, 24, 30),

                // around blacksmith
                new Coin(this.gamePanel, 13, 24),
                new SwordIron(this.gamePanel, 12, 25),
                new ArmorIron(this.gamePanel, 12, 27),
                new Torch(this.gamePanel, 8, 27),

                // bottom main island
                new ArmorGold(this.gamePanel, 22, 33),
                new PotionStrength(this.gamePanel, 23, 34),
                new ManaCrystal(this.gamePanel, 31, 31),
                new PotionHeal(this.gamePanel, 10, 36),
                new CoinBag(this.gamePanel, 12, 34),
                new PotionExperience(this.gamePanel, 11, 33),
                new PotionExperience(this.gamePanel, 7, 34),
                new CoinPile(this.gamePanel, 21, 37),
                new Coin(this.gamePanel, 17, 38),
                new PotionHeal(this.gamePanel, 30, 38),
                new PotionHeal(this.gamePanel, 25, 43),
                new CoinBag(this.gamePanel, 22, 43),

                // south island
                new ChestGold(this.gamePanel, 37, 36, new ArrayList<>(List.of(
                        new AxeGold(this.gamePanel, -1, -1),
                        new Coin(this.gamePanel, -1, -1),
                        new CoinPile(this.gamePanel, -1, -1),
                        new CoinBag(this.gamePanel, -1, -1),
                        new SwordGold(this.gamePanel, -1, -1),
                        new Coin(this.gamePanel, -1, -1),
                        new CoinPile(this.gamePanel, -1, -1),
                        new CoinBag(this.gamePanel, -1, -1),
                        new ArmorGold(this.gamePanel, -1, -1),
                        new Coin(this.gamePanel, -1, -1),
                        new CoinPile(this.gamePanel, -1, -1),
                        new CoinBag(this.gamePanel, -1, -1)
                ))),
                new KeyGold(this.gamePanel, 38, 38),
                new AxeGold(this.gamePanel, 35, 37)
        ));

        this.gamePanel.maps.get(0).entities.addAll(objects);
    }

    public void setNPCs() {
        ArrayList<Entity> npcs = new ArrayList<>(List.of(
                new OldMan(this.gamePanel, 34, 17),
                new Trader(this.gamePanel, 21, 26),
                new Blacksmith(this.gamePanel, 13, 26),
                new Farmer(this.gamePanel, 31, 15)
        ));

        this.gamePanel.maps.get(0).entities.addAll(npcs);
    }

    public void setMonsters() {
        ArrayList<Entity> monsters = new ArrayList<>(List.of(
                new MonsterSlimeGreen(this.gamePanel, 22, 42),
                new MonsterSlimeGreen(this.gamePanel, 23, 42),

                new MonsterSlimeRed(this.gamePanel, 24, 42),
                new MonsterSlimeRed(this.gamePanel, 25, 42)
        ));

        this.gamePanel.maps.get(0).entities.addAll(monsters);
    }

    public void setPortals() {
        this.portalLeaveSpawnIsland = new Portal(this.gamePanel, 8, 11);
        this.portalAccessSpawnIsland = new Portal(this.gamePanel, 16, 11);
        this.portalLeaveSouthIsland = new Portal(this.gamePanel, 41, 32);
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
                // north island
                new Bush(this.gamePanel, "bush_2", 9, 5),
                new SmallTree(this.gamePanel, 7, 6),
                new Bush(this.gamePanel, "bush_2", 5, 7),
                new Bush(this.gamePanel, "bush_1", 11, 9),
                new SmallTree(this.gamePanel, 9, 11),

                // main island
                new Wheat(this.gamePanel, 23, 10),
                new Wheat(this.gamePanel, 24, 10),
                new Wheat(this.gamePanel, 25, 10),
                new Wheat(this.gamePanel, 26, 10),
                new Wheat(this.gamePanel, 27, 10),
                new Wheat(this.gamePanel, 28, 10),
                new Wheat(this.gamePanel, 29, 10),
                new Wheat(this.gamePanel, 30, 10),
                new Wheat(this.gamePanel, 23, 11),
                new Wheat(this.gamePanel, 24, 11),
                new Wheat(this.gamePanel, 25, 11),
                new Wheat(this.gamePanel, 26, 11),
                new Wheat(this.gamePanel, 27, 11),
                new Wheat(this.gamePanel, 28, 11),
                new Wheat(this.gamePanel, 29, 11),
                new Wheat(this.gamePanel, 30, 11),
                new Wheat(this.gamePanel, 23, 12),
                new Wheat(this.gamePanel, 24, 12),
                new Wheat(this.gamePanel, 25, 12),
                new Wheat(this.gamePanel, 26, 12),
                new Wheat(this.gamePanel, 27, 12),
                new Wheat(this.gamePanel, 28, 12),
                new Wheat(this.gamePanel, 29, 12),
                new Wheat(this.gamePanel, 30, 12),
                new Wheat(this.gamePanel, 23, 13),
                new Wheat(this.gamePanel, 24, 13),
                new Wheat(this.gamePanel, 25, 13),
                new Wheat(this.gamePanel, 26, 13),
                new Wheat(this.gamePanel, 27, 13),
                new Wheat(this.gamePanel, 28, 13),
                new Wheat(this.gamePanel, 29, 13),
                new Wheat(this.gamePanel, 30, 13),
                new Wheat(this.gamePanel, 23, 14),
                new Wheat(this.gamePanel, 24, 14),
                new Wheat(this.gamePanel, 25, 14),
                new Wheat(this.gamePanel, 26, 14),
                new Wheat(this.gamePanel, 27, 14),
                new Wheat(this.gamePanel, 28, 14),
                new Wheat(this.gamePanel, 29, 14),
                new Wheat(this.gamePanel, 30, 14),
                new TreeBig(this.gamePanel, 20, 7),
                new SmallTree(this.gamePanel, 26, 7),
                new Bush(this.gamePanel, "bush_1", 19, 7),
                new Bush(this.gamePanel, "bush_2", 25, 3),
                new Bush(this.gamePanel, "bush_2", 24, 8),
                new Bush(this.gamePanel, "bush_2", 27, 9),
                new Bush(this.gamePanel, "bush_1", 28, 9),
                new Bush(this.gamePanel, "bush_2", 18, 10),
                new SmallTree(this.gamePanel, 18, 16),
                new SmallTree(this.gamePanel, 20, 17),
                new SmallTree(this.gamePanel, 33, 21),
                new Bush(this.gamePanel, "bush_1", 13, 18),
                new Bush(this.gamePanel, "bush_1", 19, 19),
                new Bush(this.gamePanel, "bush_2", 20, 19),
                new Bush(this.gamePanel, "bush_2", 20, 21),
                new SmallTree(this.gamePanel, 31, 20),
                new Bush(this.gamePanel, "bush_2", 32, 20),
                new Bush(this.gamePanel, "bush_2", 27, 21),
                new Bush(this.gamePanel, "bush_2", 28, 21),
                new Bush(this.gamePanel, "bush_1", 30, 22),
                new Bush(this.gamePanel, "bush_2", 24, 22),
                new SmallTree(this.gamePanel, 22, 25),
                new Bush(this.gamePanel, "bush_2", 23, 25),
                new Bush(this.gamePanel, "bush_2", 28, 26),
                new Bush(this.gamePanel, "bush_2", 21, 28),
                new SmallTree(this.gamePanel, 24, 28),
                new Bush(this.gamePanel, "bush_2", 18, 22),
                new Bush(this.gamePanel, "bush_2", 15, 24),
                new Bush(this.gamePanel, "bush_1", 12, 26),
                new SmallTree(this.gamePanel, 14, 26),
                new Bush(this.gamePanel, "bush_1", 11, 28),
                new SmallTree(this.gamePanel, 14, 28),
                new Bush(this.gamePanel, "bush_1", 14, 29),
                new Bush(this.gamePanel, "bush_1", 16, 30),
                new Bush(this.gamePanel, "bush_2", 33, 29),
                new SmallTree(this.gamePanel, 34, 29),
                new SmallTree(this.gamePanel, 32, 32),
                new Bush(this.gamePanel, "bush_1", 30, 34),
                new Bush(this.gamePanel, "bush_2", 23, 32),
                new Bush(this.gamePanel, "bush_2", 23, 33),
                new SmallTree(this.gamePanel, 25, 34),
                new Bush(this.gamePanel, "bush_2", 26, 34),
                new Bush(this.gamePanel, "bush_1", 8, 29),
                new SmallTree(this.gamePanel, 13, 32),
                new Bush(this.gamePanel, "bush_1", 15, 33),
                new SmallTree(this.gamePanel, 9, 34),
                new Bush(this.gamePanel, "bush_2", 14, 36),
                new Bush(this.gamePanel, "bush_2", 17, 34),
                new Bush(this.gamePanel, "bush_2", 20, 36),
                new Bush(this.gamePanel, "bush_2", 19, 37),
                new SmallTree(this.gamePanel, 20, 37),
                new Bush(this.gamePanel, "bush_2", 20, 39),
                new Bush(this.gamePanel, "bush_1", 16, 40),
                new SmallTree(this.gamePanel, 20, 40),
                new Bush(this.gamePanel, "bush_2", 22, 41),
                new Bush(this.gamePanel, "bush_1", 25, 41),

                // south island
                new Bush(this.gamePanel, "bush_1", 42, 32),
                new Bush(this.gamePanel, "bush_1", 40, 33),
                new SmallTree(this.gamePanel, 39, 36),
                new Bush(this.gamePanel, "bush_2", 38, 39),
                new Bush(this.gamePanel, "bush_2", 36, 40),

                // gates that only the axe can open
                new SmallTree(this.gamePanel, 37, 25),
                new SmallTree(this.gamePanel, 38, 25),
                new SmallTree(this.gamePanel, 36, 15),
                new SmallTree(this.gamePanel, 36, 14),
                new SmallTree(this.gamePanel, 10, 22),
                new SmallTree(this.gamePanel, 11, 21),
                new SmallTree(this.gamePanel, 12, 19),

                // extra bushes on the main island
                new Bush(this.gamePanel, "bush_2", 21, 23),
                new Bush(this.gamePanel, "bush_1", 26, 29),
                new Bush(this.gamePanel, "bush_2", 22, 12),
                new Bush(this.gamePanel, "bush_1", 18, 20),
                new Bush(this.gamePanel, "bush_2", 15, 15),
                new Bush(this.gamePanel, "bush_1", 18, 30),
                new Bush(this.gamePanel, "bush_2", 26, 24),
                new Bush(this.gamePanel, "bush_1", 8, 28),
                new Bush(this.gamePanel, "bush_2", 33, 31),
                new Bush(this.gamePanel, "bush_1", 16, 42),
                new Bush(this.gamePanel, "bush_2", 31, 35),
                new Bush(this.gamePanel, "bush_1", 25, 39)
        ));

        this.gamePanel.maps.get(0).entities.addAll(interactiveTiles);
    }

    public void setFixTiles() {
        ArrayList<TileFix> fixTiles = new ArrayList<>(List.of(
                // spawn island
                new TreeMiddle(this.gamePanel, 8, 5),
                new TreeSquares(this.gamePanel, 12, 6),
                new TreePine(this.gamePanel, 6, 7),
                new TreeMiddle(this.gamePanel, 11, 8),
                new TreeSquares(this.gamePanel, 10, 10),
                new TreeSquares(this.gamePanel, 6, 11),

                // main island
                new FarmBarn(this.gamePanel, 31, 10),
                new TreePine(this.gamePanel, 26, 6),
                new TreeBig(this.gamePanel, 28, 6),
                new TreePine(this.gamePanel, 24, 7),
                new TreeSquares(this.gamePanel, 27, 7),
                new TreeMiddle(this.gamePanel, 36, 11),
                new TreeMiddle(this.gamePanel, 29, 8),
                new TreeMiddle(this.gamePanel, 9, 36),
                new TreePine(this.gamePanel, 30, 8),
                new TreePine(this.gamePanel, 32, 9),
                new TreePine(this.gamePanel, 37, 10),
                new TreeSquares(this.gamePanel, 21, 9),
                new TreeMiddle(this.gamePanel, 26, 9),
                new TreeSquares(this.gamePanel, 19, 9),
                new TreeMiddle(this.gamePanel, 19, 13),
                new TreeMiddle(this.gamePanel, 26, 3),
                new TreeMiddle(this.gamePanel, 18, 15),
                new TreePine(this.gamePanel, 10, 19),
                new TreeMiddle(this.gamePanel, 9, 20),
                new TreeSquares(this.gamePanel, 19, 15),
                new TreeSquares(this.gamePanel, 17, 16),
                new TreeBig(this.gamePanel, 20, 16),
                new TreeMiddle(this.gamePanel, 12, 18),
                new TreeMiddle(this.gamePanel, 18, 18),
                new TreeSquares(this.gamePanel, 15, 20),
                new TreeMiddle(this.gamePanel, 28, 20),
                new TreeSquares(this.gamePanel, 36, 25),
                new TreeBig(this.gamePanel, 29, 20),
                new TreeMiddle(this.gamePanel, 32, 22),
                new TreeSquares(this.gamePanel, 33, 23),
                new TreeBig(this.gamePanel, 34, 24),
                new TreeSquares(this.gamePanel, 28, 24),
                new TreeMiddle(this.gamePanel, 26, 23),
                new TreeBig(this.gamePanel, 22, 24),
                new TreeSquares(this.gamePanel, 21, 25),
                new TreePine(this.gamePanel, 24, 25),
                new TreeSquares(this.gamePanel, 26, 25),
                new TreePine(this.gamePanel, 20, 26),
                new TreePine(this.gamePanel, 25, 27),
                new TreeSquares(this.gamePanel, 27, 27),
                new TreeSquares(this.gamePanel, 22, 28),
                new TreePine(this.gamePanel, 23, 28),
                new TreePine(this.gamePanel, 17, 22),
                new TreeMiddle(this.gamePanel, 13, 25),
                new TreePine(this.gamePanel, 14, 25),
                new TreePine(this.gamePanel, 9, 28),
                new TreeBig(this.gamePanel, 8, 26),
                new TreeSquares(this.gamePanel, 15, 28),
                new TreeBig(this.gamePanel, 16, 28),
                new TreeSquares(this.gamePanel, 18, 28),
                new TreeSquares(this.gamePanel, 13, 29),
                new TreeMiddle(this.gamePanel, 17, 30),
                new TreePine(this.gamePanel, 33, 26),
                new TreeMiddle(this.gamePanel, 13, 19),
                new TreeMiddle(this.gamePanel, 29, 30),
                new TreeBig(this.gamePanel, 30, 30),
                new TreePine(this.gamePanel, 32, 30),
                new TreePine(this.gamePanel, 28, 31),
                new TreeBig(this.gamePanel, 30, 33),
                new TreePine(this.gamePanel, 29, 34),
                new TreeMiddle(this.gamePanel, 23, 31),
                new TreePine(this.gamePanel, 22, 32),
                new TreeBig(this.gamePanel, 24, 32),
                new TreeMiddle(this.gamePanel, 26, 32),
                new TreeMiddle(this.gamePanel, 22, 34),
                new TreeMiddle(this.gamePanel, 27, 34),
                new TreeSquares(this.gamePanel, 24, 35),
                new TreeSquares(this.gamePanel, 13, 31),
                new TreeMiddle(this.gamePanel, 10, 32),
                new TreeSquares(this.gamePanel, 11, 32),
                new TreePine(this.gamePanel, 12, 32),
                new TreeMiddle(this.gamePanel, 14, 32),
                new TreeBig(this.gamePanel, 10, 35),
                new TreeSquares(this.gamePanel, 12, 35),
                new TreePine(this.gamePanel, 14, 35),
                new TreeSquares(this.gamePanel, 15, 35),
                new TreeMiddle(this.gamePanel, 19, 36),
                new TreeSquares(this.gamePanel, 21, 36),
                new TreeSquares(this.gamePanel, 17, 37),
                new TreePine(this.gamePanel, 22, 38),
                new TreePine(this.gamePanel, 16, 39),
                new TreeBig(this.gamePanel, 17, 40),
                new TreeSquares(this.gamePanel, 19, 40),
                new TreeMiddle(this.gamePanel, 24, 41),
                new TreeSquares(this.gamePanel, 27, 41),
                new TreePine(this.gamePanel, 21, 19),

                // south island
                new TreeSquares(this.gamePanel, 41, 31),
                new TreeMiddle(this.gamePanel, 38, 34),
                new TreeSquares(this.gamePanel, 41, 34),
                new TreePine(this.gamePanel, 37, 35),
                new TreeMiddle(this.gamePanel, 35, 39),
                new TreeMiddle(this.gamePanel, 39, 38)
        ));
        this.gamePanel.maps.get(0).entities.addAll(fixTiles);
    }
}
