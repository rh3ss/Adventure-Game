package main;


import entity.Entity;
import enums.GameState;
import enums.Menu;
import enums.OptionsState;
import enums.OptionsSelected;
import object.GameObject;
import object.pickup.PickUpCoin;
import object.pickup.PickUpHeart;
import object.pickup.PickUpManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GUI {
    private final GamePanel gamePanel;
    private final Font maruMonica;
    private final BufferedImage heartFull, heartBlank, playerCoins, manaCrystalFull, manaCrystalBlank;
    private Graphics2D graphics2D;
    public Menu menuSelection;
    public OptionsState optionsState;
    public OptionsSelected optionsSelected;
    public ArrayList<String> messages;
    public ArrayList<Color> messagesColor;
    public ArrayList<Integer> messagesCounter;
    public boolean messageOn, gameFinished;
    public String currentDialogueMessage;
    public int inventorySlotColumnSelected, inventorySlotRowSelected;

    public GUI(GamePanel p) {
        this.gamePanel = p;

        InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
        try {
            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (FontFormatException | IOException e) { throw new RuntimeException(e);}

        this.messages = new ArrayList<>();
        this.messagesColor = new ArrayList<>();
        this.messagesCounter = new ArrayList<>();
        this.messageOn = this.gameFinished = false;

        this.menuSelection = Menu.NEW_GAME;
        this.optionsState = OptionsState.STATE_1;
        this.optionsSelected = OptionsSelected.SELECTED_1;

        Entity heart = new PickUpHeart(this.gamePanel, -1, -1);
        this.heartFull = heart.image1;
        this.heartBlank = heart.image2;
        Entity coin = new PickUpCoin(this.gamePanel, -1, -1);
        this.playerCoins = coin.down1;
        Entity manaCrystal = new PickUpManaCrystal(this.gamePanel, -1, -1);
        this.manaCrystalFull = manaCrystal.image1;
        this.manaCrystalBlank = manaCrystal.image2;

        this.inventorySlotColumnSelected = this.inventorySlotRowSelected = 0;
    }

    public void addMessage(String message, Color color) {
        this.messages.add(message);
        this.messagesColor.add(color);
        this.messagesCounter.add(0);
    }

    public void draw(Graphics2D g2) {
        this.graphics2D = g2;
        this.graphics2D.setFont(maruMonica);
        this.graphics2D.setColor(Color.WHITE);

        switch (this.gamePanel.gameState) {
            case GameState.TITLE -> { this.drawTitleScreen(); }
            case GameState.PLAYING -> {
                this.drawPlayerHearts();
                this.drawPlayerCoins();
                this.drawPlayerMana();
                this.drawPlayerEquipment();
                this.drawMessages();
            }
            case GameState.PAUSED -> { this.drawPausedScreen(); }
            case GameState.DIALOGUE -> { this.drawDialogueScreen(); }
            case GameState.CHARACTER -> {
                this.drawPlayerAttributes();
                this.drawPlayerInventory();
            }
            case GameState.OPTIONS -> { this.drawOptionsScreen(); }
        }
    }

    private void drawTitleScreen() {
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 96F));

        String titleText = "Adventure Game";
        int xPos = this.calcXPositionForCenteredText(titleText);
        int yPos = this.gamePanel.tileSize * 3;
        // title with shadow
        this.graphics2D.setColor(Color.DARK_GRAY);
        this.graphics2D.drawString(titleText, xPos + 5, yPos + 5);
        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.drawString(titleText, xPos, yPos);
        // image
        xPos = (this.gamePanel.screenWidth / 2) - (this.gamePanel.tileSize);
        yPos += this.gamePanel.tileSize * 2;
        this.graphics2D.drawImage(this.gamePanel.player.down1, xPos, yPos, this.gamePanel.tileSize * 2, this.gamePanel.tileSize * 2, null);
        // menu
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.BOLD, 48F));
        String optionText = "New Game";
        xPos = this.calcXPositionForCenteredText(optionText);
        yPos += this.gamePanel.tileSize * 3;
        this.graphics2D.drawString(optionText, xPos, yPos);
        if (this.menuSelection == Menu.NEW_GAME) {
            this.graphics2D.drawString(">", xPos - this.gamePanel.tileSize, yPos);
        }
        optionText = "Load Game";
        xPos = this.calcXPositionForCenteredText(optionText);
        yPos += this.gamePanel.tileSize;
        this.graphics2D.drawString(optionText, xPos, yPos);
        if (this.menuSelection == Menu.LOAD_GAME) {
            this.graphics2D.drawString(">", xPos - this.gamePanel.tileSize, yPos);
        }
        optionText = "Quit";
        xPos = this.calcXPositionForCenteredText(optionText);
        yPos += this.gamePanel.tileSize;
        this.graphics2D.drawString(optionText, xPos, yPos);
        if (this.menuSelection == Menu.QUIT) {
            this.graphics2D.drawString(">", xPos - this.gamePanel.tileSize, yPos);
        }

    }

    private void drawPlayerHearts() {
        int xPos = this.gamePanel.tileSize / 2;
        int yPos = this.gamePanel.tileSize / 2;
        // current hearts
        for (int i = 0; i < this.gamePanel.player.currentHearts; i++) {
            this.graphics2D.drawImage(this.heartFull, xPos, yPos, null);
            xPos += this.gamePanel.tileSize;
        }
        // remaining hearts
        for (int i = 0; i < this.gamePanel.player.maxHearts - this.gamePanel.player.currentHearts; i++) {
            this.graphics2D.drawImage(this.heartBlank, xPos, yPos, null);
            xPos += this.gamePanel.tileSize;
        }
    }

    private void drawPlayerMana() {
        int xPos = (this.gamePanel.tileSize / 2) + 1;
        int yPos = (int) (this.gamePanel.tileSize * 1.5);
        // current mana
        for (int i = 0; i < this.gamePanel.player.currentMana; i++) {
            this.graphics2D.drawImage(this.manaCrystalFull, xPos, yPos, null);
            xPos += this.gamePanel.tileSize;
        }
        // remaining mana
        for (int i = 0; i < this.gamePanel.player.maxMana - this.gamePanel.player.currentMana; i++) {
            this.graphics2D.drawImage(this.manaCrystalBlank, xPos, yPos, null);
            xPos += this.gamePanel.tileSize;
        }
    }

    private void drawPlayerCoins() {
        int xPos = (int) (this.gamePanel.screenWidth - (this.gamePanel.tileSize * 1.25));
        int yPos = this.gamePanel.tileSize / 2;
        // current coins value
        String textValueOfCoins = String.valueOf(this.gamePanel.player.coins);
        int textX = this.calcXPositionForAlignToRightText(textValueOfCoins, xPos - 20);
        // draw
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.BOLD, 40F));
        FontMetrics fontMetrics = this.graphics2D.getFontMetrics();
        int textY = yPos + (this.playerCoins.getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        this.graphics2D.setColor(Color.BLACK);
        this.graphics2D.drawString(textValueOfCoins, textX + 2, textY + 2);
        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.drawString(textValueOfCoins, textX, textY);
        // coin image
        this.graphics2D.drawImage(this.playerCoins, xPos, yPos, null);
    }

    private void drawPlayerEquipment() {
        int equipmentPosX = this.gamePanel.tileSize / 2;
        int equipmentPosY = (int) (this.gamePanel.screenHeight - (this.gamePanel.tileSize * 1.5));
        int blackShadowWidth = 2;

        List<Image> equipmentImages = new ArrayList<>(List.of(
                this.gamePanel.player.currentWeapon.down1,
                this.gamePanel.player.currentShield.down1,
                this.gamePanel.player.currentArmor.down1
        ));
        for (Image image : equipmentImages) {
            this.graphics2D.setColor(new Color(0x000000));
            this.graphics2D.fillRoundRect(equipmentPosX - blackShadowWidth, equipmentPosY - blackShadowWidth, this.gamePanel.tileSize + blackShadowWidth * 2, this.gamePanel.tileSize + blackShadowWidth * 2, 10, 10);
            this.graphics2D.setColor(new Color(0xf0be5a));
            this.graphics2D.fillRoundRect(equipmentPosX, equipmentPosY, this.gamePanel.tileSize, this.gamePanel.tileSize, 10, 10);
            this.graphics2D.drawImage(image, equipmentPosX, equipmentPosY, null);
            equipmentPosX += (this.gamePanel.tileSize + (blackShadowWidth * 2));
        }
    }

    private void drawMessages() {
        int messageX = this.gamePanel.tileSize;
        int messageY = this.gamePanel.tileSize * 4;
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.BOLD, 26F));

        for (int idx = 0; idx < this.messages.size(); idx++) {
            String idxMessage = this.messages.get(idx);
            if (idxMessage != null) {
                this.graphics2D.setColor(Color.BLACK);
                this.graphics2D.drawString(idxMessage, messageX + 2, messageY + 2);
                this.graphics2D.setColor(this.messagesColor.get(idx));
                this.graphics2D.drawString(idxMessage, messageX, messageY);
                int counter =  this.messagesCounter.get(idx) + 1;
                this.messagesCounter.set(idx, counter);
                messageY += 30;

                if (this.messagesCounter.get(idx) > (this.gamePanel.FPS * 3)) {
                    this.messages.remove(idx);
                    this.messagesColor.remove(idx);
                    this.messagesCounter.remove(idx);
                }
            }
        }
    }

    private void drawPausedScreen() {
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 80F));

        String pausedText = "PAUSED";
        int xPos = this.calcXPositionForCenteredText(pausedText);
        int yPos = this.calcYPositionForCenteredText(pausedText);
        this.graphics2D.drawString(pausedText, xPos, yPos);
    }

    private void drawDialogueScreen() {
        // draw window
        int xPos = this.gamePanel.tileSize * 2;
        int yPos = this.gamePanel.tileSize / 2;
        int width = this.gamePanel.screenWidth - (this.gamePanel.tileSize * 4);
        int height = this.gamePanel.tileSize * 4;
        this.drawSubWindowScreen(xPos, yPos, width, height);
        // draw text
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 28F));
        xPos += this.gamePanel.tileSize;
        yPos += this.gamePanel.tileSize;
        for (String line : this.currentDialogueMessage.split("\n")) {
            this.graphics2D.drawString(line, xPos, yPos);
            yPos += 40;
        }
    }

    private void drawPlayerAttributes() {
        // create a frame
        int frameX = this.gamePanel.tileSize * 2;
        int frameY = this.gamePanel.tileSize;
        int frameWidth = this.gamePanel.tileSize * 5;
        int frameHeight = this.gamePanel.tileSize * 10;
        this.drawSubWindowScreen(frameX, frameY, frameWidth, frameHeight);

        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + this.gamePanel.tileSize;
        int lineSpacing = 35;

        // attribute text
        this.graphics2D.drawString("Level", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Hearts", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Mana", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Strength", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Dexterity", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Attack", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Defense", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Experience", textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawString("Next Level Exp.", textX, textY);
        textY += lineSpacing + 10;
        this.graphics2D.drawString("Weapon", textX, textY);
        textY += lineSpacing + 15;
        this.graphics2D.drawString("Shield", textX, textY);

        // attribute values
        int rightX = (frameX + frameWidth) - 30;
        textY = frameY + this.gamePanel.tileSize;

        String textValue = String.valueOf(this.gamePanel.player.currentLevel);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf((double) Math.round(this.gamePanel.player.currentHearts) + "/" + this.gamePanel.player.maxHearts);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.currentMana + "/" + this.gamePanel.player.maxMana);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf((double) Math.round(this.gamePanel.player.strength * 100) / 100);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf((double) Math.round(this.gamePanel.player.dexterity * 100) / 100);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = "+";
        double attackDamae = (double) Math.round(this.gamePanel.player.attackDamage * 100);
        textValue += String.valueOf(attackDamae);
        textValue += "%";
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = "+";
        double defenseArmor = (double) Math.round(this.gamePanel.player.defenseArmor * 100);
        textValue += String.valueOf(defenseArmor);
        textValue += "%";
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.currentExperience);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.nextLevelExperience);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        this.graphics2D.drawImage(this.gamePanel.player.currentWeapon.down1, rightX - this.gamePanel.tileSize, textY - 24, null);
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawImage(this.gamePanel.player.currentShield.down1, rightX - this.gamePanel.tileSize, textY - 24, null);
    }

    private void drawPlayerInventory() {
        // inventory window frame
        int inventoryWindowFrameX = this.gamePanel.tileSize * 12;
        int inventoryWindowFrameY = this.gamePanel.tileSize;
        int inventoryWindowFrameWidth = this.gamePanel.tileSize * (this.gamePanel.player.inventoryColumnSize + 1);
        int inventoryWindowFrameHeight = this.gamePanel.tileSize * (this.gamePanel.player.inventoryRowSize + 1);
        this.drawSubWindowScreen(inventoryWindowFrameX, inventoryWindowFrameY, inventoryWindowFrameWidth, inventoryWindowFrameHeight);
        // players inventory items
        int inventorySlotStartX = inventoryWindowFrameX + 20;
        int inventorySlotStartY = inventoryWindowFrameY + 20;
        int inventorySlotX = inventorySlotStartX;
        int inventorySlotY = inventorySlotStartY;
        for (int idx = 1; idx < this.gamePanel.player.inventory.size() + 1; idx++) {
            Entity item = this.gamePanel.player.inventory.get(idx - 1);
            // highlight players equipped items
            if (item == this.gamePanel.player.currentWeapon || item == this.gamePanel.player.currentShield || item == this.gamePanel.player.currentArmor) {
                this.graphics2D.setColor(new Color(240, 190, 90));
                this.graphics2D.fillRoundRect(inventorySlotX, inventorySlotY, this.gamePanel.tileSize, this.gamePanel.tileSize, 10, 10);
            }
            this.graphics2D.drawImage(item.down1, inventorySlotX, inventorySlotY, null);
            inventorySlotX += this.gamePanel.tileSize;
            if (idx % this.gamePanel.player.inventoryColumnSize == 0) {
                inventorySlotX = inventorySlotStartX;
                inventorySlotY += this.gamePanel.tileSize;
            }
        }
        // inventory selected cursor
        int inventoryCursorX = inventorySlotStartX + (this.gamePanel.tileSize * this.inventorySlotColumnSelected);
        int inventoryCursorY = inventorySlotStartY + (this.gamePanel.tileSize * this.inventorySlotRowSelected);
        int inventoryCursorWidth = this.gamePanel.tileSize;
        int inventoryCursorHeight = this.gamePanel.tileSize;
        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.setStroke(new BasicStroke(3));
        this.graphics2D.drawRoundRect(inventoryCursorX, inventoryCursorY, inventoryCursorWidth, inventoryCursorHeight, 10, 10);
        // inventory selected item description
        int descriptionFrameX = inventoryWindowFrameX;
        int descriptionFrameY = inventoryWindowFrameY + inventoryWindowFrameHeight;
        int descriptionFrameWidth = inventoryWindowFrameWidth;
        int descriptionFrameHeight = this.gamePanel.tileSize * 3;
        int descriptionTextX = descriptionFrameX + 20;
        int descriptionTextY = descriptionFrameY + this.gamePanel.tileSize;
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(28F));
        int itemIndex = this.getSelectedInventoryItemIndexOnSlot();
        if (itemIndex < this.gamePanel.player.inventory.size()) {
            this.drawSubWindowScreen(descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight);
            GameObject object = (GameObject) this.gamePanel.player.inventory.get(itemIndex);
            String objectDescription = object.objectDescription;
            for (String line : objectDescription.split("\n")) {
                this.graphics2D.drawString(line, descriptionTextX, descriptionTextY);
                descriptionTextY += 32;
            }
        }
    }

    private void drawOptionsScreen() {
        this.graphics2D.setColor(new Color(0xffffff));
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(32F));

        int optionsWindowFrameX = this.gamePanel.tileSize * 6;
        int optionsWindowFrameY = this.gamePanel.tileSize;
        int optionsWindowFrameWidth = this.gamePanel.tileSize * 8;
        int optionsWindowFrameHeight = this.gamePanel.tileSize * 10;
        this.drawSubWindowScreen(optionsWindowFrameX, optionsWindowFrameY, optionsWindowFrameWidth, optionsWindowFrameHeight);

        switch (this.optionsState) {
            case OptionsState.STATE_1 -> { this.optionsTop(optionsWindowFrameX, optionsWindowFrameY); }
            case OptionsState.STATE_2 -> { this.optionsFullScreenNotification(optionsWindowFrameX, optionsWindowFrameY); }
            case OptionsState.STATE_3 -> { this.optionsControls(optionsWindowFrameX, optionsWindowFrameY); }
            case OptionsState.STATE_4 -> { this.optionsEndGameConfirmation(optionsWindowFrameX, optionsWindowFrameY); }
        }

        this.gamePanel.keyboard.isEnterPressed = false;
    }

    private void optionsTop(int optionsWindowFrameX, int optionsWindowFrameY) {
        String optionsText = "Options";
        int textX = this.calcXPositionForCenteredText(optionsText);
        int textY = optionsWindowFrameY + this.gamePanel.tileSize;
        this.graphics2D.drawString(optionsText, textX, textY);

        textX = optionsWindowFrameX + this.gamePanel.tileSize;
        // full screen
        textY += this.gamePanel.tileSize * 2;
        this.graphics2D.drawString("Full Screen", textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_1) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.gamePanel.fullScreenOn = !this.gamePanel.fullScreenOn;
                this.optionsState = OptionsState.STATE_2;
            }
        }
        // control
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Control", textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_2) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_3;
                this.optionsSelected = OptionsSelected.SELECTED_1;
            }
        }
        // end game
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("End Game", textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_3) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_4;
                this.optionsSelected = OptionsSelected.SELECTED_1;
            }
        }
        // back
        textY += this.gamePanel.tileSize * 4;
        this.graphics2D.drawString("Back", textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_4) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.gamePanel.gameState = GameState.PLAYING;
                this.optionsSelected = OptionsSelected.SELECTED_1;
            }
        }
        // full screen check box
        textX = optionsWindowFrameX + this.gamePanel.tileSize * 5;
        textY = optionsWindowFrameY + (this.gamePanel.tileSize * 2) + (this.gamePanel.tileSize / 2);
        this.graphics2D.setStroke(new BasicStroke(3));
        this.graphics2D.drawRect(textX, textY, (this.gamePanel.tileSize / 2), (this.gamePanel.tileSize / 2));
        if (this.gamePanel.fullScreenOn) {
            this.graphics2D.fillRect(textX, textY, (this.gamePanel.tileSize / 2), (this.gamePanel.tileSize / 2));
        }
        // save current options
        this.gamePanel.config.saveCurrentConfig();
    }

    private void optionsFullScreenNotification(int optionsWindowFrameX, int optionsWindowFrameY) {
        int textX = optionsWindowFrameX + this.gamePanel.tileSize;
        int textY = optionsWindowFrameY + (this.gamePanel.tileSize * 3);

        this.currentDialogueMessage = "The change will take \neffect after restarting \nthe game.";
        for (String line : this.currentDialogueMessage.split("\n")) {
            this.graphics2D.drawString(line, textX, textY);
            textY += 40;
        }
        // back
        textY = optionsWindowFrameY + (this.gamePanel.tileSize * 9);
        this.graphics2D.drawString("Back", textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_1) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_1;
            }
        }
    }

    private void optionsControls(int optionsWindowFrameX, int optionsWindowFrameY) {
        String optionsText = "Controls";
        int textX = this.calcXPositionForCenteredText(optionsText);
        int textY = optionsWindowFrameY + this.gamePanel.tileSize;
        this.graphics2D.drawString(optionsText, textX, textY);

        textX = optionsWindowFrameX + this.gamePanel.tileSize;
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Move", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Confirm/Attack", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Shooting", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Inventory", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Pause", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Options", textX, textY);

        textX = optionsWindowFrameX + (this.gamePanel.tileSize * 6);
        textY = optionsWindowFrameY + (this.gamePanel.tileSize * 2);
        this.graphics2D.drawString("WASD", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("ENTER", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("F", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("C", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("P", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("ESC", textX, textY);

        // back
        textX = optionsWindowFrameX + this.gamePanel.tileSize;
        textY = optionsWindowFrameY + (this.gamePanel.tileSize * 9);
        this.graphics2D.drawString("Back", textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_1) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_1;
                this.optionsSelected = OptionsSelected.SELECTED_2;
            }
        }

    }

    private void optionsEndGameConfirmation(int optionsWindowFrameX, int optionsWindowFrameY) {
        int textX = optionsWindowFrameX + this.gamePanel.tileSize;
        int textY = optionsWindowFrameY + (this.gamePanel.tileSize * 3);

        this.currentDialogueMessage = "Quit the game and return \nto the title screen?";
        for (String line : this.currentDialogueMessage.split("\n")) {
            this.graphics2D.drawString(line, textX, textY);
            textY += 40;
        }
        // yes
        String text = "Yes";
        textX = this.calcXPositionForCenteredText(text);
        textY += this.gamePanel.tileSize * 3;
        this.graphics2D.drawString(text, textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_1) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_1;
                this.gamePanel.gameState = GameState.TITLE;
            }
        }
        // no
        text = "No";
        textX = this.calcXPositionForCenteredText(text);
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawString(text, textX, textY);
        if (this.optionsSelected == OptionsSelected.SELECTED_2) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_1;
                this.optionsSelected = OptionsSelected.SELECTED_3;
            }
        }
    }

    public int getSelectedInventoryItemIndexOnSlot() {
        return this.inventorySlotColumnSelected + (this.inventorySlotRowSelected * 5);
    }

    private void drawSubWindowScreen(int x, int y, int width, int height) {
        int arcSize = 35;

        this.graphics2D.setColor(new Color(0, 0, 0, 200));
        this.graphics2D.fillRoundRect(x, y, width, height, arcSize, arcSize);

        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.setStroke(new BasicStroke(5));
        this.graphics2D.drawRoundRect(x + 5, y + 5, width - 10 , height - 10, arcSize - 10, arcSize - 10);
    }

    private int calcXPositionForCenteredText(String text) {
        int textLength = (int) this.graphics2D.getFontMetrics().getStringBounds(text, this.graphics2D).getWidth();
        return (this.gamePanel.screenWidth / 2) - (textLength / 2);
    }

    private int calcYPositionForCenteredText(String text) {
        int textHeight = (int) this.graphics2D.getFontMetrics().getStringBounds(text, this.graphics2D).getHeight();
        return (this.gamePanel.screenHeight / 2) - (textHeight / 2);
    }

    private int calcXPositionForAlignToRightText(String text, int rightX) {
        int textLength = (int) this.graphics2D.getFontMetrics().getStringBounds(text, this.graphics2D).getWidth();
        return rightX - textLength;
    }
}
