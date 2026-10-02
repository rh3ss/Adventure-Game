package main;


import algorithm.PathFinder;
import entity.Entity;
import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import javax.swing.JPanel;

import enums.GameState;
import environment.EnvironmentManager;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    private final int originalTileSize = 16;
    private final int scale = 3;
    public final int tileSize = this.originalTileSize * this.scale;

    // SCREEN
    public final int maxScreenColumns = 26;
    public final int maxScreenRows = 15;
    public final int screenWidth = this.tileSize * this.maxScreenColumns;   // 1248px
    public final int screenHeight = this.tileSize * this.maxScreenRows;     // 720px

    // FULL SCREEN
    public int screenWidthFull = this.screenWidth;
    public int screenHeightFull = this.screenHeight;
    public BufferedImage fullScreen;
    public Graphics2D graphics2D;
    public boolean fullScreenOn;

    // WORLD
    public final int maxWorldColumns = 100;
    public final int maxWorldRows = 100;
    public final int worldWidth = this.tileSize * this.maxWorldColumns;
    public final int worldHeight = this.tileSize * this.maxWorldRows;

    // MAP
    public final int maxNumberOfMaps = 5;
    public int currentMapNumber = 0;

    // CLASSES
    public Keyboard keyboard = new Keyboard(this);
    public TileManager tileManager = new TileManager(this);
    public CollisionDetector collisionDetector = new CollisionDetector(this);
    public Map<Integer, MapData> maps = new HashMap<>();
    public AssetSetter assetSetter = new AssetSetter(this);
    public GUI gui = new GUI(this);
    public EventHandler eventHandler = new EventHandler(this);
    public PathFinder pathFinder = new PathFinder(this);
    public EnvironmentManager environmentManager = new EnvironmentManager(this);
    public Config config = new Config(this);
    public Player player = new Player(this, this.keyboard);

    // GAME
    public GameState gameState;
    public Thread gameThread;
    public final int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyboard);
        this.setFocusable(true);
        this.fullScreenOn = false;
    }

    public void setupGame() {
        this.assetSetter.setAllAssetSetter();
        this.environmentManager.setup();
        this.gameState = GameState.TITLE;
        // set drawing to the new bufferedImage graphic
        this.fullScreen = new BufferedImage(this.screenWidth, this.screenHeight, BufferedImage.TYPE_INT_ARGB);
        this.graphics2D = (Graphics2D) this.fullScreen.getGraphics();
        // set local game to current device full screen
        if (this.fullScreenOn) {
            this.setFullScreen();
        }
    }

    public void respawn() {
        this.player.setDefaultValuesAfterRespawn();
    }

    public void restart() {
        this.maps.get(this.currentMapNumber).entities.clear();
        this.assetSetter.setAllAssetSetter();
        this.player.setDefaultValues();
        this.player.setInventoryObjects();
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

    public void quitGame() {
        this.gameThread = null;
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / this.FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        // GAME LOOP
        while(this.gameThread != null) {
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
        System.exit(0);
    }

    public void update() {
        if (this.gameState == GameState.PLAYING) {
            // update all entities (npc, monster, projectiles)
            for (int idx = 0; idx < this.maps.get(this.currentMapNumber).entities.size(); idx++) {
                Entity entity = this.maps.get(this.currentMapNumber).entities.get(idx);
                if (entity != null) {
                    if (entity.isAlive && !entity.isDying) {
                        entity.update();
                    }
                    else if (!entity.isAlive){
                        entity.chooseObjectToDrop();
                        this.maps.get(this.currentMapNumber).entities.set(idx, null);
                    }
                }
            }
            environmentManager.update();
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
        this.maps.get(this.currentMapNumber).entities.removeIf(Objects::isNull);
        this.maps.get(this.currentMapNumber).entities.sort(Comparator.comparingInt(entity -> entity.worldY));

        // draw entities
        for (Entity entity : this.maps.get(this.currentMapNumber).entities) {
            if (entity != null) {
                entity.draw(this.graphics2D);
            }
        }

        // environment
        this.environmentManager.draw(this.graphics2D);

        // GUI
        this.gui.draw(this.graphics2D);
    }
}
