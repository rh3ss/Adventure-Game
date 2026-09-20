package main;


import enums.GameState;
import enums.Menu;
import enums.OptionsState;
import enums.OptionsSelected;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
    private final GamePanel gamePanel;
    public boolean isUpPressed, isDownPressed, isLeftPressed, isRightPressed, isEnterPressed, isShootingPressed;

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
                this.titleKeyEvent(keyboardCode);
            }
            case GameState.PLAYING -> {
                this.playingKeyEvent(keyboardCode);
            }
            case GameState.PAUSED -> {
                if (keyboardCode == KeyEvent.VK_P) { this.gamePanel.gameState = GameState.PLAYING; }
            }
            case GameState.DIALOGUE -> {
                if (keyboardCode == KeyEvent.VK_ENTER) { this.gamePanel.gameState = GameState.PLAYING; }
            }
            case GameState.CHARACTER -> {
                this.characterKeyEvent(keyboardCode);
            }
            case GameState.OPTIONS -> {
                this.optionsKeyEvent(keyboardCode);
            }
        }
    }

    private void titleKeyEvent(int keyboardCode) {
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

    private void playingKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_W) { this.isUpPressed = true; }
        if (keyboardCode == KeyEvent.VK_S) { this.isDownPressed = true; }
        if (keyboardCode == KeyEvent.VK_A) { this.isLeftPressed = true; }
        if (keyboardCode == KeyEvent.VK_D) { this.isRightPressed = true; }
        if (keyboardCode == KeyEvent.VK_ENTER) { this.isEnterPressed = true; }
        if (keyboardCode == KeyEvent.VK_F) { this.isShootingPressed = true; }
        if (keyboardCode == KeyEvent.VK_P) { this.gamePanel.gameState = GameState.PAUSED; }
        if (keyboardCode == KeyEvent.VK_C) { this.gamePanel.gameState = GameState.CHARACTER; }
        if (keyboardCode == KeyEvent.VK_ESCAPE) { this.gamePanel.gameState = GameState.OPTIONS; }
    }

    private void characterKeyEvent(int keyboardCode) {
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
        if (keyboardCode == KeyEvent.VK_ENTER) {
            this.gamePanel.player.equipCurrentSelectedInventoryItem();
        }
    }

    private void optionsKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_ESCAPE) { this.gamePanel.gameState = GameState.PLAYING; }
        if (keyboardCode == KeyEvent.VK_ENTER) { this.isEnterPressed = true; }

        if (keyboardCode == KeyEvent.VK_UP) {
            switch (this.gamePanel.gui.optionsState) {
                case OptionsState.STATE_1 -> {
                    switch (this.gamePanel.gui.optionsSelected) {
                        case OptionsSelected.SELECTED_1 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_4; }
                        case OptionsSelected.SELECTED_2 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_1; }
                        case OptionsSelected.SELECTED_3 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_2; }
                        case OptionsSelected.SELECTED_4 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_3; }
                    }
                }
                case OptionsState.STATE_2, OptionsState.STATE_3 -> {
                    // nothing
                }
                case OptionsState.STATE_4 -> {
                    switch (this.gamePanel.gui.optionsSelected) {
                        case OptionsSelected.SELECTED_1 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_2; }
                        case OptionsSelected.SELECTED_2 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_1; }
                    }
                }
            }

        }
        if (keyboardCode == KeyEvent.VK_DOWN) {
            switch (this.gamePanel.gui.optionsState) {
                case OptionsState.STATE_1 -> {
                    switch (this.gamePanel.gui.optionsSelected) {
                        case OptionsSelected.SELECTED_1 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_2; }
                        case OptionsSelected.SELECTED_2 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_3; }
                        case OptionsSelected.SELECTED_3 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_4; }
                        case OptionsSelected.SELECTED_4 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_1; }
                    }
                }
                case OptionsState.STATE_2, OptionsState.STATE_3 -> {
                    // nothing
                }
                case OptionsState.STATE_4 -> {
                    switch (this.gamePanel.gui.optionsSelected) {
                        case OptionsSelected.SELECTED_2 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_1; }
                        case OptionsSelected.SELECTED_1 -> { this.gamePanel.gui.optionsSelected = OptionsSelected.SELECTED_2; }
                    }
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyboardCode = e.getKeyCode();

        if (keyboardCode == KeyEvent.VK_W) { this.isUpPressed = false; }
        if (keyboardCode == KeyEvent.VK_S) { this.isDownPressed = false; }
        if (keyboardCode == KeyEvent.VK_A) { this.isLeftPressed = false; }
        if (keyboardCode == KeyEvent.VK_D) { this.isRightPressed = false; }
        if (keyboardCode == KeyEvent.VK_F) { this.isShootingPressed = false; }
    }
    
}
