package main;

import entity.NPCOldMan;
import monster.MonsterSlimeGreen;
import monster.MonsterSlimeRed;
import object.*;
import object.pickup.PickUpHeart;
import object.pickup.PickUpManaCrystal;
import object.pickup.PickUpCoin;
import object.potion.*;
import object.shield.ShieldBlue;
import object.weapon.AxeIron;
import object.weapon.SwordGold;
import object.weapon.SwordIron;
import tileInteractive.InteractiveTile;
import tileInteractive.InteractiveTileBush;
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
        KeySilver key1 = new KeySilver(this.gamePanel, 27, 25);
        KeyGold key2 = new KeyGold(this.gamePanel, 28, 25);
        PickUpCoin coin1 = new PickUpCoin(this.gamePanel, 22, 23);
        PickUpCoin coin2 = new PickUpCoin(this.gamePanel, 23, 23);
        PickUpHeart heart1 = new PickUpHeart(this.gamePanel, 25, 23);
        PickUpManaCrystal mana1 = new PickUpManaCrystal(this.gamePanel, 26, 23);
        ObjectDoor door1 = new ObjectDoor(this.gamePanel, 22, 42);
        Potion potion1 = new PotionExperience(this.gamePanel, 21, 25);
        Potion potion2 = new PotionHeal(this.gamePanel, 21, 26);
        Potion potion3 = new PotionSpeed(this.gamePanel, 21, 27);
        Potion potion4 = new PotionStrength(this.gamePanel, 21, 28);
        AxeIron axe1 = new AxeIron(this.gamePanel, 23, 25);
        ShieldBlue shieldBlue1 = new ShieldBlue(this.gamePanel, 23, 26);
        SwordIron swordIron1 = new SwordIron(this.gamePanel, 23, 27);
        SwordGold swordGold1 = new SwordGold(this.gamePanel, 23, 28);

        this.gamePanel.entities.add(key1);
        this.gamePanel.entities.add(key2);
        this.gamePanel.entities.add(coin1);
        this.gamePanel.entities.add(coin2);
        this.gamePanel.entities.add(heart1);
        this.gamePanel.entities.add(mana1);
        this.gamePanel.entities.add(door1);
        this.gamePanel.entities.add(axe1);
        this.gamePanel.entities.add(shieldBlue1);
        this.gamePanel.entities.add(potion1);
        this.gamePanel.entities.add(potion2);
        this.gamePanel.entities.add(potion3);
        this.gamePanel.entities.add(potion4);
        this.gamePanel.entities.add(swordIron1);
        this.gamePanel.entities.add(swordGold1);
    }

    public void setNPCs() {
        NPCOldMan oldMan = new NPCOldMan(this.gamePanel, 21, 21);
        this.gamePanel.entities.add(oldMan);
    }

    public void setMonsters() {
        MonsterSlimeGreen slime = new MonsterSlimeGreen(this.gamePanel, 23, 38);
        MonsterSlimeGreen slime1 = new MonsterSlimeGreen(this.gamePanel, 23, 40);
        MonsterSlimeGreen slime2 = new MonsterSlimeGreen(this.gamePanel, 23, 43);
        MonsterSlimeGreen slime3 = new MonsterSlimeGreen(this.gamePanel, 23, 42);

        MonsterSlimeRed monsterSlimeRed1 = new MonsterSlimeRed(this.gamePanel, 21, 38);
        MonsterSlimeRed monsterSlimeRed2 = new MonsterSlimeRed(this.gamePanel, 21, 40);
        MonsterSlimeRed monsterSlimeRed3 = new MonsterSlimeRed(this.gamePanel, 21, 43);
        MonsterSlimeRed monsterSlimeRed4 = new MonsterSlimeRed(this.gamePanel, 21, 42);

        this.gamePanel.entities.add(slime);
        this.gamePanel.entities.add(slime1);
        this.gamePanel.entities.add(slime2);
        this.gamePanel.entities.add(slime3);
        this.gamePanel.entities.add(monsterSlimeRed1);
        this.gamePanel.entities.add(monsterSlimeRed2);
        this.gamePanel.entities.add(monsterSlimeRed3);
        this.gamePanel.entities.add(monsterSlimeRed4);
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
