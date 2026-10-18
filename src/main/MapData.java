package main;

import entity.Entity;
import environment.Lighting;

import java.util.ArrayList;

public class MapData {
    public final GamePanel gamePanel;

    // WORLD
    public final int maxWorldColumns;
    public final int maxWorldRows;

    // CLASSES
    public ArrayList<Entity> entities = new ArrayList<>();
    public Lighting lighting;

    public MapData(GamePanel gamePanel, int maxWorldColumns, int maxWorldRows) {
        this.gamePanel = gamePanel;
        this.maxWorldColumns = maxWorldColumns;
        this.maxWorldRows = maxWorldRows;
    }
}
