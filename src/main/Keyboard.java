package main;


import enums.*;

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
            case GameState.TITLE -> { this.titleKeyEvent(keyboardCode); }
            case GameState.PLAYING -> { this.playingKeyEvent(keyboardCode); }
            case GameState.PAUSED -> { this.pausedKeyEvent(keyboardCode); }
            case GameState.DIALOGUE -> { this.dialogueKeyEvent(keyboardCode); }
            case GameState.CHARACTER -> { this.characterKeyEvent(keyboardCode); }
            case GameState.OPTIONS -> { this.optionsKeyEvent(keyboardCode); }
            case GameState.TRADING -> { this.tradingKeyEvent(keyboardCode); }
            case GameState.GAME_OVER -> { this.gameOverKeyEvent(keyboardCode); }
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
                case Menu.QUIT -> { this.gamePanel.quitGame(); }
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

    private void pausedKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_P) { this.gamePanel.gameState = GameState.PLAYING; }
    }

    private void dialogueKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_ENTER) { this.gamePanel.gameState = GameState.PLAYING; }
    }

    private void characterKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_C) { this.gamePanel.gameState = GameState.PLAYING; }

        if (keyboardCode == KeyEvent.VK_ENTER) {
            this.gamePanel.player.equipCurrentSelectedInventoryObject();
        }
        this.playerInventoryKeyEvent(keyboardCode);
    }

    private void playerInventoryKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_UP) {
            this.gamePanel.gui.playerInventorySlotRowSelected--;
            if (this.gamePanel.gui.playerInventorySlotRowSelected < 0) {
                this.gamePanel.gui.playerInventorySlotRowSelected = this.gamePanel.player.inventoryRowSize - 1;
            }
        }
        if (keyboardCode == KeyEvent.VK_DOWN) {
            this.gamePanel.gui.playerInventorySlotRowSelected++;
            if (this.gamePanel.gui.playerInventorySlotRowSelected > this.gamePanel.player.inventoryRowSize - 1) {
                this.gamePanel.gui.playerInventorySlotRowSelected = 0;
            }
        }
        if (keyboardCode == KeyEvent.VK_LEFT) {
            this.gamePanel.gui.playerInventorySlotColumnSelected--;
            if (this.gamePanel.gui.playerInventorySlotColumnSelected < 0) {
                this.gamePanel.gui.playerInventorySlotColumnSelected = this.gamePanel.player.inventoryColumnSize - 1;
            }
        }
        if (keyboardCode == KeyEvent.VK_RIGHT) {
            this.gamePanel.gui.playerInventorySlotColumnSelected++;
            if (this.gamePanel.gui.playerInventorySlotColumnSelected > this.gamePanel.player.inventoryColumnSize - 1) {
                this.gamePanel.gui.playerInventorySlotColumnSelected = 0;
            }
        }
    }

    private void npcInventoryKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_UP) {
            this.gamePanel.gui.npcInventorySlotRowSelected--;
            if (this.gamePanel.gui.npcInventorySlotRowSelected < 0) {
                this.gamePanel.gui.npcInventorySlotRowSelected = this.gamePanel.gui.interactedNPC.inventoryRowSize - 1;
            }
        }
        if (keyboardCode == KeyEvent.VK_DOWN) {
            this.gamePanel.gui.npcInventorySlotRowSelected++;
            if (this.gamePanel.gui.npcInventorySlotRowSelected > this.gamePanel.gui.interactedNPC.inventoryRowSize - 1) {
                this.gamePanel.gui.npcInventorySlotRowSelected = 0;
            }
        }
        if (keyboardCode == KeyEvent.VK_LEFT) {
            this.gamePanel.gui.npcInventorySlotColumnSelected--;
            if (this.gamePanel.gui.npcInventorySlotColumnSelected < 0) {
                this.gamePanel.gui.npcInventorySlotColumnSelected = this.gamePanel.gui.interactedNPC.inventoryColumnSize - 1;
            }
        }
        if (keyboardCode == KeyEvent.VK_RIGHT) {
            this.gamePanel.gui.npcInventorySlotColumnSelected++;
            if (this.gamePanel.gui.npcInventorySlotColumnSelected > this.gamePanel.gui.interactedNPC.inventoryColumnSize - 1) {
                this.gamePanel.gui.npcInventorySlotColumnSelected = 0;
            }
        }
    }

    private void optionsKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_ESCAPE) { this.gamePanel.gameState = GameState.PLAYING; }
        if (keyboardCode == KeyEvent.VK_ENTER) { this.isEnterPressed = true; }

        if (keyboardCode == KeyEvent.VK_UP) {
            switch (this.gamePanel.gui.optionsState) {
                case OptionsState.STATE_1 -> {
                    switch (this.gamePanel.gui.optionsSelection) {
                        case OptionsSelection.SELECTED_1 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_4; }
                        case OptionsSelection.SELECTED_2 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_1; }
                        case OptionsSelection.SELECTED_3 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_2; }
                        case OptionsSelection.SELECTED_4 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_3; }
                    }
                }
                case OptionsState.STATE_2, OptionsState.STATE_3 -> {
                    // nothing
                }
                case OptionsState.STATE_4 -> {
                    switch (this.gamePanel.gui.optionsSelection) {
                        case OptionsSelection.SELECTED_1 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_2; }
                        case OptionsSelection.SELECTED_2 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_1; }
                    }
                }
            }

        }
        if (keyboardCode == KeyEvent.VK_DOWN) {
            switch (this.gamePanel.gui.optionsState) {
                case OptionsState.STATE_1 -> {
                    switch (this.gamePanel.gui.optionsSelection) {
                        case OptionsSelection.SELECTED_1 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_2; }
                        case OptionsSelection.SELECTED_2 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_3; }
                        case OptionsSelection.SELECTED_3 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_4; }
                        case OptionsSelection.SELECTED_4 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_1; }
                    }
                }
                case OptionsState.STATE_2, OptionsState.STATE_3 -> {
                    // nothing
                }
                case OptionsState.STATE_4 -> {
                    switch (this.gamePanel.gui.optionsSelection) {
                        case OptionsSelection.SELECTED_2 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_1; }
                        case OptionsSelection.SELECTED_1 -> { this.gamePanel.gui.optionsSelection = OptionsSelection.SELECTED_2; }
                    }
                }
            }
        }
    }

    private void tradingKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_ENTER) { this.isEnterPressed = true; }

        if (keyboardCode == KeyEvent.VK_UP) {
            switch (this.gamePanel.gui.tradingSelection) {
                case TradingSelection.BUY -> { this.gamePanel.gui.tradingSelection = TradingSelection.LEAVE; }
                case TradingSelection.SELL -> { this.gamePanel.gui.tradingSelection = TradingSelection.BUY; }
                case TradingSelection.LEAVE -> { this.gamePanel.gui.tradingSelection = TradingSelection.SELL; }
            }
        }
        if (keyboardCode == KeyEvent.VK_DOWN) {
            switch (this.gamePanel.gui.tradingSelection) {
                case TradingSelection.BUY -> { this.gamePanel.gui.tradingSelection = TradingSelection.SELL; }
                case TradingSelection.SELL -> { this.gamePanel.gui.tradingSelection = TradingSelection.LEAVE; }
                case TradingSelection.LEAVE -> { this.gamePanel.gui.tradingSelection = TradingSelection.BUY; }
            }
        }
        // cursor npc inventory
        if (this.gamePanel.gui.tradingState == TradingState.BUY) {
            this.npcInventoryKeyEvent(keyboardCode);
            if (keyboardCode == KeyEvent.VK_ESCAPE) {
                this.gamePanel.gui.tradingState = TradingState.SELECT;
            }
        }
        if (this.gamePanel.gui.tradingState == TradingState.SELL) {
            this.playerInventoryKeyEvent(keyboardCode);
            if (keyboardCode == KeyEvent.VK_ESCAPE) {
                this.gamePanel.gui.tradingState = TradingState.SELECT;
            }
        }
    }

    private void gameOverKeyEvent(int keyboardCode) {
        if (keyboardCode == KeyEvent.VK_UP || keyboardCode == KeyEvent.VK_DOWN) {
            if (this.gamePanel.gui.gameOverSelection == GameOverSelection.NEGATIVE) {
                this.gamePanel.gui.gameOverSelection = GameOverSelection.RESPAWN;
            }
            else if (this.gamePanel.gui.gameOverSelection == GameOverSelection.RESPAWN) {
                this.gamePanel.gui.gameOverSelection = GameOverSelection.QUIT;
            }
            else if (this.gamePanel.gui.gameOverSelection == GameOverSelection.QUIT) {
                this.gamePanel.gui.gameOverSelection = GameOverSelection.RESPAWN;
            }
        }
        if (keyboardCode == KeyEvent.VK_ENTER) {
            switch (this.gamePanel.gui.gameOverSelection) {
                case GameOverSelection.RESPAWN -> {
                    this.gamePanel.gameState = GameState.PLAYING;
                    this.gamePanel.respawn();
                }
                case GameOverSelection.QUIT -> { 
                    this.gamePanel.gameState = GameState.TITLE;
                    this.gamePanel.restart();
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
