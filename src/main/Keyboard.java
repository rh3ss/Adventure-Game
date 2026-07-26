package main;


import enums.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    private final GamePanel gamePanel;
    public boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed; 

    public Keyboard(GamePanel p) {
        this.gamePanel = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyboardCode = e.getKeyCode();

        if (keyboardCode == KeyEvent.VK_UP) { this.isUpPressed = true; }
        if (keyboardCode == KeyEvent.VK_DOWN) { this.isDownPressed = true; }
        if (keyboardCode == KeyEvent.VK_LEFT) { this.isLeftPressed = true; }
        if (keyboardCode == KeyEvent.VK_RIGHT) { this.isRightPressed = true; }
        if (keyboardCode == KeyEvent.VK_P) {
            this.gamePanel.gameState = (this.gamePanel.gameState == GameState.PLAYING) ? GameState.PAUSED : GameState.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyboardCode = e.getKeyCode();

        if(keyboardCode == KeyEvent.VK_UP) { this.isUpPressed = false; }
        if(keyboardCode == KeyEvent.VK_DOWN) { this.isDownPressed = false; }
        if(keyboardCode == KeyEvent.VK_LEFT) { this.isLeftPressed = false; }
        if(keyboardCode == KeyEvent.VK_RIGHT) { this.isRightPressed = false; }
    }
    
}
