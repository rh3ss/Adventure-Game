package main;

import entity.Entity;
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

        MapData world = new MapData(gamePanel, 100, 100);
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
                new ChestIron(this.gamePanel, 34, 31, new ArrayList<>(List.of(
                        new Coin(this.gamePanel, -1, -1),
                        new Coin(this.gamePanel, -1, -1),
                        new CoinPile(this.gamePanel, -1, -1),
                        new CoinPile(this.gamePanel, -1, -1),
                        new CoinBag(this.gamePanel, -1, -1),
                        new CoinBag(this.gamePanel, -1, -1)
                ))),
                new AxeIron(this.gamePanel, 32, 32),
                new PotionStrength(this.gamePanel, 35, 34),
                new ManaCrystal(this.gamePanel, 37, 32),

                // main islandw
                new CoinBag(this.gamePanel, 43, 42),
                new CoinPile(this.gamePanel, 46, 42),
                new PotionSpeed(this.gamePanel, 44, 41),
                new PotionHeal(this.gamePanel, 54, 29),
                new ManaCrystal(this.gamePanel, 56, 33),
                new PotionStrength(this.gamePanel, 57, 49),
                new ChestIron(this.gamePanel, 62, 36, new ArrayList<>(List.of(
                        new KeySilver(this.gamePanel, -1, -1)
                ))),
                new Torch(this.gamePanel, 62, 38),

                // 6) blacksmith's cove in the north-west (3 small trees block the way, needs the axe): iron equipment
                new SwordIron(this.gamePanel, 34, 45),
                new AxeIron(this.gamePanel, 34, 46),
                new ShieldWood(this.gamePanel, 35, 44),
                new ArmorIron(this.gamePanel, 36, 44),
                new KeySilver(this.gamePanel, 34, 47),

                // 7) south island (portal hidden behind 2 small trees): gold chest (existing) + treasure on the ground
                new ChestGold(this.gamePanel, 62, 61, new ArrayList<>(List.of(
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
                new KeyGold(this.gamePanel, 64, 58),
                new CoinBag(this.gamePanel, 64, 60),
                new AxeGold(this.gamePanel, 61, 62),
                new SwordGold(this.gamePanel, 62, 62),
                new ArmorGold(this.gamePanel, 63, 62),
                new ShieldBlue(this.gamePanel, 61, 63)
        ));

        this.gamePanel.maps.get(0).entities.addAll(objects);
    }

    public void setNPCs() {
        ArrayList<Entity> npcs = new ArrayList<>(List.of(
                // old man: guide at the start, trader: at the portal to the spawn island, blacksmith: hidden in his cove
                new OldMan(this.gamePanel, 59, 42),
                new Trader(this.gamePanel, 43, 38),
                new Blacksmith(this.gamePanel, 35, 45),
                new Farmer(this.gamePanel, 56, 40)
        ));

        this.gamePanel.maps.get(0).entities.addAll(npcs);
    }

    public void setMonsters() {
        ArrayList<Entity> monsters = new ArrayList<>(List.of(
//                new MonsterSlimeGreen(this.gamePanel, 46, 66),
//                new MonsterSlimeGreen(this.gamePanel, 47, 66),
//
//                new MonsterSlimeRed(this.gamePanel, 48, 66),
//                new MonsterSlimeRed(this.gamePanel, 49, 66)
        ));

        this.gamePanel.maps.get(0).entities.addAll(monsters);
    }

    public void setPortals() {
        this.portalLeaveSpawnIsland = new Portal(this.gamePanel, 33, 36);
        this.portalAccessSpawnIsland = new Portal(this.gamePanel, 41, 36);
        this.portalLeaveSouthIsland = new Portal(this.gamePanel, 66, 57);
        this.portalAccessSouthIsland = new Portal(this.gamePanel, 63, 49);

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
                new Bush(this.gamePanel, "bush_2", 34, 30),
                new SmallTree(this.gamePanel, 32, 31),
                new Bush(this.gamePanel, "bush_2", 30, 32),
                new Bush(this.gamePanel, "bush_1", 36, 34),
                new SmallTree(this.gamePanel, 34, 36),

                // main island
                new Wheat(this.gamePanel, 48, 35),
                new Wheat(this.gamePanel, 49, 35),
                new Wheat(this.gamePanel, 50, 35),
                new Wheat(this.gamePanel, 51, 35),
                new Wheat(this.gamePanel, 52, 35),
                new Wheat(this.gamePanel, 53, 35),
                new Wheat(this.gamePanel, 54, 35),
                new Wheat(this.gamePanel, 55, 35),
                new Wheat(this.gamePanel, 48, 36),
                new Wheat(this.gamePanel, 49, 36),
                new Wheat(this.gamePanel, 50, 36),
                new Wheat(this.gamePanel, 51, 36),
                new Wheat(this.gamePanel, 52, 36),
                new Wheat(this.gamePanel, 53, 36),
                new Wheat(this.gamePanel, 54, 36),
                new Wheat(this.gamePanel, 55, 36),
                new Wheat(this.gamePanel, 48, 37),
                new Wheat(this.gamePanel, 49, 37),
                new Wheat(this.gamePanel, 50, 37),
                new Wheat(this.gamePanel, 51, 37),
                new Wheat(this.gamePanel, 52, 37),
                new Wheat(this.gamePanel, 53, 37),
                new Wheat(this.gamePanel, 54, 37),
                new Wheat(this.gamePanel, 55, 37),
                new Wheat(this.gamePanel, 48, 38),
                new Wheat(this.gamePanel, 49, 38),
                new Wheat(this.gamePanel, 50, 38),
                new Wheat(this.gamePanel, 51, 38),
                new Wheat(this.gamePanel, 52, 38),
                new Wheat(this.gamePanel, 53, 38),
                new Wheat(this.gamePanel, 54, 38),
                new Wheat(this.gamePanel, 55, 38),
                new Wheat(this.gamePanel, 48, 39),
                new Wheat(this.gamePanel, 49, 39),
                new Wheat(this.gamePanel, 50, 39),
                new Wheat(this.gamePanel, 51, 39),
                new Wheat(this.gamePanel, 52, 39),
                new Wheat(this.gamePanel, 53, 39),
                new Wheat(this.gamePanel, 54, 39),
                new Wheat(this.gamePanel, 55, 39),
                new TreeBig(this.gamePanel, 45, 32),
                new SmallTree(this.gamePanel, 51, 32),
                new Bush(this.gamePanel, "bush_1", 44, 32),
                new Bush(this.gamePanel, "bush_2", 50, 28),
                new Bush(this.gamePanel, "bush_2", 49, 33),
                new Bush(this.gamePanel, "bush_2", 52, 34),
                new Bush(this.gamePanel, "bush_1", 53, 34),
                new Bush(this.gamePanel, "bush_2", 43, 35),
                new SmallTree(this.gamePanel, 43, 41),
                new SmallTree(this.gamePanel, 45, 42),
                new Bush(this.gamePanel, "bush_1", 38, 43),
                new Bush(this.gamePanel, "bush_1", 44, 44),
                new Bush(this.gamePanel, "bush_2", 45, 44),
                new Bush(this.gamePanel, "bush_2", 45, 46),
                new SmallTree(this.gamePanel, 56, 45),
                new Bush(this.gamePanel, "bush_2", 57, 45),
                new Bush(this.gamePanel, "bush_2", 52, 46),
                new Bush(this.gamePanel, "bush_2", 53, 46),
                new Bush(this.gamePanel, "bush_1", 55, 47),
                new Bush(this.gamePanel, "bush_2", 49, 47),
                new SmallTree(this.gamePanel, 47, 50),
                new Bush(this.gamePanel, "bush_2", 48, 50),
                new Bush(this.gamePanel, "bush_2", 53, 51),
                new Bush(this.gamePanel, "bush_2", 46, 53),
                new SmallTree(this.gamePanel, 49, 53),
                new Bush(this.gamePanel, "bush_2", 43, 47),
                new Bush(this.gamePanel, "bush_2", 40, 49),
                new Bush(this.gamePanel, "bush_1", 37, 51),
                new SmallTree(this.gamePanel, 39, 51),
                new Bush(this.gamePanel, "bush_1", 36, 53),
                new SmallTree(this.gamePanel, 39, 53),
                new Bush(this.gamePanel, "bush_1", 39, 54),
                new Bush(this.gamePanel, "bush_1", 41, 55),
                new Bush(this.gamePanel, "bush_2", 58, 54),
                new SmallTree(this.gamePanel, 59, 54),
                new SmallTree(this.gamePanel, 57, 57),
                new Bush(this.gamePanel, "bush_1", 55, 59),
                new Bush(this.gamePanel, "bush_2", 48, 57),
                new Bush(this.gamePanel, "bush_2", 48, 58),
                new SmallTree(this.gamePanel, 50, 59),
                new Bush(this.gamePanel, "bush_2", 51, 59),
                new Bush(this.gamePanel, "bush_1", 33, 54),
                new SmallTree(this.gamePanel, 38, 57),
                new Bush(this.gamePanel, "bush_1", 40, 58),
                new SmallTree(this.gamePanel, 34, 59),
                new Bush(this.gamePanel, "bush_2", 39, 61),
                new Bush(this.gamePanel, "bush_2", 42, 59),
                new Bush(this.gamePanel, "bush_2", 45, 61),
                new Bush(this.gamePanel, "bush_2", 44, 62),
                new SmallTree(this.gamePanel, 45, 62),
                new Bush(this.gamePanel, "bush_2", 45, 64),
                new Bush(this.gamePanel, "bush_1", 41, 65),
                new SmallTree(this.gamePanel, 45, 65),
                new Bush(this.gamePanel, "bush_2", 47, 66),
                new Bush(this.gamePanel, "bush_1", 50, 66),

                // south island
                new Bush(this.gamePanel, "bush_1", 67, 57),
                new Bush(this.gamePanel, "bush_1", 65, 58),
                new SmallTree(this.gamePanel, 64, 61),
                new Bush(this.gamePanel, "bush_2", 63, 64),
                new Bush(this.gamePanel, "bush_2", 61, 65),

                // gates that only the axe can open
                // south portal pocket (2 trees in a row)
                new SmallTree(this.gamePanel, 62, 50),
                new SmallTree(this.gamePanel, 63, 50),
                // behind the barn (2 trees in a row)
                new SmallTree(this.gamePanel, 61, 40),
                new SmallTree(this.gamePanel, 61, 39),
                // entrances of the blacksmith's cove
                new SmallTree(this.gamePanel, 35, 47),
                new SmallTree(this.gamePanel, 36, 46),
                new SmallTree(this.gamePanel, 37, 44),

                // extra bushes on the main island
                new Bush(this.gamePanel, "bush_1", 59, 48),
                new Bush(this.gamePanel, "bush_2", 46, 48),
                new Bush(this.gamePanel, "bush_1", 51, 54),
                new Bush(this.gamePanel, "bush_2", 47, 37),
                new Bush(this.gamePanel, "bush_1", 43, 45),
                new Bush(this.gamePanel, "bush_2", 40, 40),
                new Bush(this.gamePanel, "bush_1", 43, 55),
                new Bush(this.gamePanel, "bush_2", 51, 49),
                new Bush(this.gamePanel, "bush_1", 33, 53),
                new Bush(this.gamePanel, "bush_2", 58, 56),
                new Bush(this.gamePanel, "bush_1", 41, 67),
                new Bush(this.gamePanel, "bush_2", 56, 60),
                new Bush(this.gamePanel, "bush_1", 50, 64)
        ));

        this.gamePanel.maps.get(0).entities.addAll(interactiveTiles);
    }

    public void setFixTiles() {
        ArrayList<TileFix> fixTiles = new ArrayList<>(List.of(
                // spawn island
                new TreeMiddle(this.gamePanel, 33, 30),
                new TreeSquares(this.gamePanel, 37, 31),
                new TreePine(this.gamePanel, 31, 32),
                new TreeMiddle(this.gamePanel, 36, 33),
                new TreeSquares(this.gamePanel, 35, 35),
                new TreeSquares(this.gamePanel, 31, 36),

                // main island
                new FarmBarn(this.gamePanel, 56, 35),
                new TreePine(this.gamePanel, 51, 31),
                new TreeBig(this.gamePanel, 53, 31),
                new TreePine(this.gamePanel, 49, 32),
                new TreeSquares(this.gamePanel, 52, 32),
                new TreeMiddle(this.gamePanel, 61, 36),
                new TreeMiddle(this.gamePanel, 54, 33),
                new TreeMiddle(this.gamePanel, 35, 60),
                new TreePine(this.gamePanel, 55, 33),
                new TreePine(this.gamePanel, 57, 34),
                new TreePine(this.gamePanel, 62, 35),
                new TreeSquares(this.gamePanel, 46, 34),
                new TreeMiddle(this.gamePanel, 51, 34),
                new TreeSquares(this.gamePanel, 44, 34),
                new TreeMiddle(this.gamePanel, 44, 38),
                new TreeMiddle(this.gamePanel, 51, 28),
                new TreeMiddle(this.gamePanel, 43, 40),
                new TreeSquares(this.gamePanel, 44, 40),
                new TreeSquares(this.gamePanel, 42, 41),
                new TreeBig(this.gamePanel, 45, 41),
                new TreeMiddle(this.gamePanel, 37, 43),
                new TreeMiddle(this.gamePanel, 43, 43),
                new TreeSquares(this.gamePanel, 40, 45),
                new TreeMiddle(this.gamePanel, 53, 45),
                new TreeSquares(this.gamePanel, 61, 50),
                new TreeBig(this.gamePanel, 54, 45),
                new TreeMiddle(this.gamePanel, 57, 47),
                new TreeSquares(this.gamePanel, 58, 48),
                new TreeBig(this.gamePanel, 59, 49),
                new TreeSquares(this.gamePanel, 53, 49),
                new TreeMiddle(this.gamePanel, 51, 48),
                new TreeBig(this.gamePanel, 47, 49),
                new TreeSquares(this.gamePanel, 46, 50),
                new TreePine(this.gamePanel, 49, 50),
                new TreeSquares(this.gamePanel, 51, 50),
                new TreePine(this.gamePanel, 45, 51),
                new TreePine(this.gamePanel, 50, 52),
                new TreeSquares(this.gamePanel, 52, 52),
                new TreeSquares(this.gamePanel, 47, 53),
                new TreePine(this.gamePanel, 48, 53),
                new TreePine(this.gamePanel, 42, 47),
                new TreeMiddle(this.gamePanel, 38, 50),
                new TreePine(this.gamePanel, 39, 50),
                new TreePine(this.gamePanel, 34, 53),
                new TreeSquares(this.gamePanel, 40, 53),
                new TreeBig(this.gamePanel, 41, 53),
                new TreeSquares(this.gamePanel, 43, 53),
                new TreeSquares(this.gamePanel, 38, 54),
                new TreeMiddle(this.gamePanel, 42, 55),
                new TreePine(this.gamePanel, 58, 51), //
                new TreeMiddle(this.gamePanel, 54, 55),
                new TreeBig(this.gamePanel, 55, 55),
                new TreePine(this.gamePanel, 57, 55),
                new TreePine(this.gamePanel, 53, 56),
                new TreeBig(this.gamePanel, 55, 58),
                new TreePine(this.gamePanel, 54, 59),
                new TreeMiddle(this.gamePanel, 48, 56),
                new TreePine(this.gamePanel, 47, 57),
                new TreeBig(this.gamePanel, 49, 57),
                new TreeMiddle(this.gamePanel, 51, 57),
                new TreeMiddle(this.gamePanel, 47, 59),
                new TreeMiddle(this.gamePanel, 52, 59),
                new TreeSquares(this.gamePanel, 49, 60),
                new TreeSquares(this.gamePanel, 38, 56),
                new TreeMiddle(this.gamePanel, 35, 57),
                new TreeSquares(this.gamePanel, 36, 57),
                new TreePine(this.gamePanel, 37, 57),
                new TreeMiddle(this.gamePanel, 39, 57),
                new TreeBig(this.gamePanel, 35, 60),
                new TreeSquares(this.gamePanel, 37, 60),
                new TreePine(this.gamePanel, 39, 60),
                new TreeSquares(this.gamePanel, 40, 60),
                new TreeMiddle(this.gamePanel, 44, 61),
                new TreeSquares(this.gamePanel, 46, 61),
                new TreeSquares(this.gamePanel, 42, 62),
                new TreePine(this.gamePanel, 47, 63),
                new TreePine(this.gamePanel, 41, 64),
                new TreeBig(this.gamePanel, 42, 65),
                new TreeSquares(this.gamePanel, 44, 65),
                new TreeMiddle(this.gamePanel, 49, 66),
                new TreeSquares(this.gamePanel, 52, 66),
                new TreePine(this.gamePanel, 46, 44),

                // south island
                new TreeSquares(this.gamePanel, 66, 56),
                new TreeMiddle(this.gamePanel, 63, 59),
                new TreeSquares(this.gamePanel, 66, 59),
                new TreePine(this.gamePanel, 62, 60),
                new TreeMiddle(this.gamePanel, 60, 64),
                new TreeMiddle(this.gamePanel, 64, 63)
        ));
        this.gamePanel.maps.get(0).entities.addAll(fixTiles);
    }
}
