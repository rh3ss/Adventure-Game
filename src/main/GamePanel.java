package main;


import entity.Entity;
import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import javax.swing.JPanel;

import enums.GameState;
import enums.Menu;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = this.originalTileSize * this.scale;

    // SCREEN
    public final int maxScreenColumns = 16;
    public final int maxScreenRows = 12;
    public final int screenWidth = this.tileSize * this.maxScreenColumns;   // 768px
    public final int screenHeight = this.tileSize * this.maxScreenRows;     // 576px

    // WORLD
    public final int maxWorldColumns = 50;
    public final int maxWorldRows = 50;
    public final int worldWidth = this.tileSize * this.maxWorldColumns;
    public final int worldHeight = this.tileSize * this.maxWorldRows;

    public final int FPS = 60;

    public Keyboard keyboard = new Keyboard(this);
    public TileManager tileManager = new TileManager(this);
    public CollisionDetector collisionDetector = new CollisionDetector(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public GUI gui = new GUI(this);
    public EventHandler eventHandler = new EventHandler(this);
    public Player player = new Player(this, this.keyboard);
    public ArrayList<Entity> entities = new ArrayList<>();

    public GameState gameState;
    public Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyboard);
        this.setFocusable(true);
        this.gameState = GameState.PREPARING;
    }

    public void setupGame() {
        this.assetSetter.setPlayer();
        this.assetSetter.setObjects();
        this.assetSetter.setNPCs();
        this.assetSetter.setMonster();
        this.gameState = GameState.TITLE;
        this.gui.menuSelection = Menu.NEW_GAME;
    }

    public void startGame() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / this.FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // GAME LOOP
        while(this.gameState != GameState.GAME_OVER) {
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            
            if(deltaTime >= 1) {
                update();
                repaint();
                deltaTime--;
            }
        }
        this.gameThread = null;
    }

    public void update() {
        if (this.gameState == GameState.PLAYING) {
            for (int idx = 0; idx < this.entities.size(); idx++) {
                Entity entity = this.entities.get(idx);
                if (entity != null) {
                    if (entity.isAlive && !entity.isDying) {
                        entity.update();
                    }
                    else if (!entity.isAlive){
                        this.entities.set(idx, null);
                    }
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (this.gameState == GameState.TITLE) {
            this.drawTitleScreen(g2);
        }
        else {
            this.drawGame(g2);
        }
        g2.dispose();
    }

    private void drawTitleScreen(Graphics2D g2) {
        // GUI
        this.gui.draw(g2);
    }

    private void drawGame(Graphics2D g2) {
        // draw map layer
        this.tileManager.draw(g2);

        // sorting entities by worldY pos
        entities.removeIf(Objects::isNull);
        entities.sort(Comparator.comparingInt(entity -> entity.worldY));

        // draw entities
        for (Entity entity : entities) {
            if (entity != null) { entity.draw(g2); }
        }
        // GUI
        this.gui.draw(g2);
    }
}
