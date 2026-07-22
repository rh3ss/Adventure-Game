package main;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    public boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed; 

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyboardCode = e.getKeyCode();

        if(keyboardCode == KeyEvent.VK_UP) { this.isUpPressed = true; }
        if(keyboardCode == KeyEvent.VK_DOWN) { this.isDownPressed = true; }
        if(keyboardCode == KeyEvent.VK_LEFT) { this.isLeftPressed = true; }
        if(keyboardCode == KeyEvent.VK_RIGHT) { this.isRightPressed = true; }
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
