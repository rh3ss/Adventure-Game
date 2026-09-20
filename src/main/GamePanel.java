package main;


import entity.Entity;
import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import javax.swing.JPanel;

import enums.GameState;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = this.originalTileSize * this.scale;

    // SCREEN
    public final int maxScreenColumns = 20;
    public final int maxScreenRows = 12;
    public final int screenWidth = this.tileSize * this.maxScreenColumns;   // 960px
    public final int screenHeight = this.tileSize * this.maxScreenRows;     // 576px

    // FULL SCREEN
    public int screenWidthFull = this.screenWidth;
    public int screenHeightFull = this.screenHeight;
    public BufferedImage fullScreen;
    public Graphics2D graphics2D;
    public boolean fullScreenOn;

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
    public Config config = new Config(this);
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
        this.fullScreenOn = false;
    }

    public void setupGame() {
        this.assetSetter.setPlayer();
        this.assetSetter.setObjects();
        this.assetSetter.setNPCs();
        this.assetSetter.setMonsters();
        this.assetSetter.setInteractiveTiles();
        this.gameState = GameState.TITLE;
        // set drawing to the new bufferedImage graphic
        this.fullScreen = new BufferedImage(this.screenWidth, this.screenHeight, BufferedImage.TYPE_INT_ARGB);
        this.graphics2D = (Graphics2D) this.fullScreen.getGraphics();
        // set local game to current device full screen
        if (this.fullScreenOn) {
            this.setFullScreen();
        }
    }

    public void setFullScreen() {
        // get local screen device
        GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
        graphicsDevice.setFullScreenWindow(Main.window);
        // get full screen width and height
        this.screenWidthFull = Main.window.getWidth();
        this.screenHeightFull = Main.window.getHeight();
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
                this.drawGameScreen();      // draw everything to BufferedImage
                this.drawToFullScreen();    // draw BufferedImage to the screen
                deltaTime--;
            }
        }
        this.gameThread = null;
    }

    public void update() {
        if (this.gameState == GameState.PLAYING) {
            // draw all entities (npc, monster, projectiles)
            for (int idx = 0; idx < this.entities.size(); idx++) {
                Entity entity = this.entities.get(idx);
                if (entity != null) {
                    if (entity.isAlive && !entity.isDying) {
                        entity.update();
                    }
                    else if (!entity.isAlive){
                        entity.chooseObjectToDrop();
                        this.entities.set(idx, null);
                    }
                }
            }
        }
    }

    public void drawGameScreen() {
        if (this.gameState == GameState.TITLE) {
            this.drawTitleScreen();
        }
        else {
            this.drawGame();
        }
    }

    public void drawToFullScreen() {
        Graphics g = this.getGraphics();
        g.drawImage(this.fullScreen, 0, 0, this.screenWidthFull, screenHeightFull, null);
        g.dispose();
    }

    private void drawTitleScreen() {
        // GUI
        this.gui.draw(this.graphics2D);
    }

    private void drawGame() {
        // draw map layer
        this.tileManager.draw(this.graphics2D);

        // sorting entities by worldY pos
        entities.removeIf(Objects::isNull);
        entities.sort(Comparator.comparingInt(entity -> entity.worldY));

        // draw entities
        for (Entity entity : entities) {
            if (entity != null) { entity.draw(this.graphics2D); }
        }
        // GUI
        this.gui.draw(this.graphics2D);
    }
}
