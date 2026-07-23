package main;


import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

import object.GameObject;
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

    public Keyboard keyboard = new Keyboard();
    public TileManager tileManager = new TileManager(this);
    public CollisionDetector collisionDetector = new CollisionDetector(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, this.keyboard);
    public ArrayList<GameObject> objects = new ArrayList<>();
    public GUI gui = new GUI(this);

    public Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(this.screenWidth, this.screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyboard);
        this.setFocusable(true);
    }

    public void setupGame() {
        this.assetSetter.setObjects();
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
        while(this.gameThread != null) {
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            
            if(deltaTime >= 1) {
                update();
                repaint();
                deltaTime--;
            }
        }
    }

    public void update() {
        this.player.update();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        this.drawGame(g2);
        g2.dispose();
    }

    private void drawGame(Graphics2D g2) {
        // draw map layer
        this.tileManager.draw(g2);
        // draw object layer
        for (GameObject object : objects) {
            if (object != null) { object.draw(g2, this); }
        }
        // last draw player
        this.player.draw(g2);
        // GUI
        this.gui.draw(g2);
    }
}
