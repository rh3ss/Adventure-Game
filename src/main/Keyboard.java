package main;


import enums.GameState;
import enums.Menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    private final GamePanel gamePanel;
    public boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isEnterPressed;

    public Keyboard(GamePanel p) {
        this.gamePanel = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyboardCode = e.getKeyCode();

        switch (this.gamePanel.gameState) {
            case GameState.TITLE -> {
                if (keyboardCode == KeyEvent.VK_UP) {
                    switch (this.gamePanel.gui.menuSelection) {
                        case Menu.NEW_GAME -> { this.gamePanel.gui.menuSelection = Menu.QUIT; }
                        case Menu.LOAD_GAME -> { this.gamePanel.gui.menuSelection = Menu.NEW_GAME; }
                        case Menu.QUIT -> { this.gamePanel.gui.menuSelection = Menu.LOAD_GAME; }
                    }
                }
                if (keyboardCode == KeyEvent.VK_DOWN) {
                    switch (this.gamePanel.gui.menuSelection) {
                        case Menu.NEW_GAME -> { this.gamePanel.gui.menuSelection = Menu.LOAD_GAME; }
                        case Menu.LOAD_GAME -> { this.gamePanel.gui.menuSelection = Menu.QUIT; }
                        case Menu.QUIT -> { this.gamePanel.gui.menuSelection = Menu.NEW_GAME; }
                    }
                }
                if (keyboardCode == KeyEvent.VK_ENTER) {
                    switch (this.gamePanel.gui.menuSelection) {
                        case Menu.NEW_GAME -> { this.gamePanel.gameState = GameState.PLAYING; }
                        case Menu.LOAD_GAME -> {
                            // later
                        }
                        case Menu.QUIT -> { System.exit(0); }
                    }
                }
            }
            case GameState.PLAYING -> {
                if (keyboardCode == KeyEvent.VK_W) { this.isUpPressed = true; }
                if (keyboardCode == KeyEvent.VK_S) { this.isDownPressed = true; }
                if (keyboardCode == KeyEvent.VK_A) { this.isLeftPressed = true; }
                if (keyboardCode == KeyEvent.VK_D) { this.isRightPressed = true; }
                if (keyboardCode == KeyEvent.VK_ENTER) { this.isEnterPressed = true; }
                if (keyboardCode == KeyEvent.VK_P) { this.gamePanel.gameState = GameState.PAUSED; }
                if (keyboardCode == KeyEvent.VK_C) { this.gamePanel.gameState = GameState.CHARACTER; }
            }
            case GameState.PAUSED -> {
                if (keyboardCode == KeyEvent.VK_P) { this.gamePanel.gameState = GameState.PLAYING; }
            }
            case GameState.DIALOGUE -> {
                if (keyboardCode == KeyEvent.VK_ENTER) { this.gamePanel.gameState = GameState.PLAYING; }
            }
            case GameState.CHARACTER -> {
                if (keyboardCode == KeyEvent.VK_C) { this.gamePanel.gameState = GameState.PLAYING; }
                if (keyboardCode == KeyEvent.VK_W) {
                    this.gamePanel.gui.inventorySlotRowSelected--;
                    if (this.gamePanel.gui.inventorySlotRowSelected < 0) {
                        this.gamePanel.gui.inventorySlotRowSelected = this.gamePanel.player.inventoryRowSize - 1;
                    }
                }
                if (keyboardCode == KeyEvent.VK_S) {
                    this.gamePanel.gui.inventorySlotRowSelected++;
                    if (this.gamePanel.gui.inventorySlotRowSelected > this.gamePanel.player.inventoryRowSize - 1) {
                        this.gamePanel.gui.inventorySlotRowSelected = 0;
                    }
                }
                if (keyboardCode == KeyEvent.VK_A) {
                    this.gamePanel.gui.inventorySlotColumnSelected--;
                    if (this.gamePanel.gui.inventorySlotColumnSelected < 0) {
                        this.gamePanel.gui.inventorySlotColumnSelected = this.gamePanel.player.inventoryColumnSize - 1;
                    }
                }
                if (keyboardCode == KeyEvent.VK_D) {
                    this.gamePanel.gui.inventorySlotColumnSelected++;
                    if (this.gamePanel.gui.inventorySlotColumnSelected > this.gamePanel.player.inventoryColumnSize - 1) {
                        this.gamePanel.gui.inventorySlotColumnSelected = 0;
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyboardCode = e.getKeyCode();

        if(keyboardCode == KeyEvent.VK_W) { this.isUpPressed = false; }
        if(keyboardCode == KeyEvent.VK_S) { this.isDownPressed = false; }
        if(keyboardCode == KeyEvent.VK_A) { this.isLeftPressed = false; }
        if(keyboardCode == KeyEvent.VK_D) { this.isRightPressed = false; }
    }
    
}
