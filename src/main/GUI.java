package main;


import entity.Entity;
import enums.GameState;
import enums.Menu;
import object.ObjectHeart;
import object.ObjectManaCrystal;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.BasicStroke;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GUI {
    private final GamePanel gamePanel;
    private final Font maruMonica;
    private final BufferedImage heartFull, heartBlank, manaCrystalFull, manaCrystalBlank;
    private Graphics2D graphics2D;
    public Menu menuSelection;
    public ArrayList<String> messages;
    public ArrayList<Integer> messagesCounter;
    public boolean messageOn;
    public boolean gameFinished;
    public String currentDialogueMessage;
    public BufferedImage healthBarHeart;
    public int inventorySlotColumnSelected, inventorySlotRowSelected;

    public GUI(GamePanel p) {
        this.gamePanel = p;

        InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
        try {
            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (FontFormatException | IOException e) { throw new RuntimeException(e);}

        this.messages = new ArrayList<>();
        this.messagesCounter = new ArrayList<>();
        this.messageOn = this.gameFinished = false;

        Entity heart = new ObjectHeart(this.gamePanel, -1, -1);
        this.heartFull = heart.image1;
        this.heartBlank = heart.image2;
        this.healthBarHeart = heart.setupEntityImage("/res/objects/health_bar_heart.png", this.gamePanel.tileSize, this.gamePanel.tileSize);
        Entity manaCrystal = new ObjectManaCrystal(this.gamePanel, -1, -1);
        this.manaCrystalFull = manaCrystal.image1;
        this.manaCrystalBlank = manaCrystal.image2;

        this.inventorySlotColumnSelected = this.inventorySlotRowSelected = 0;
    }

    public void addMessage(String message) {
        this.messages.add(message);
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
                this.drawPlayerMana();
                this.drawMessages();
            }
            case GameState.PAUSED -> { this.drawPausedScreen(); }
            case GameState.DIALOGUE -> { this.drawDialogueScreen(); }
            case GameState.CHARACTER -> {
                this.drawPlayerAttributes();
                this.drawPlayerInventory();
            }
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

    private void drawMessages() {
        int messageX = this.gamePanel.tileSize;
        int messageY = this.gamePanel.tileSize * 4;
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.BOLD, 26F));

        for (int idx = 0; idx < this.messages.size(); idx++) {
            String idxMessage = this.messages.get(idx);
            if (idxMessage != null) {
                this.graphics2D.setColor(Color.BLACK);
                this.graphics2D.drawString(idxMessage, messageX + 2, messageY + 2);
                this.graphics2D.setColor(Color.WHITE);
                this.graphics2D.drawString(idxMessage, messageX, messageY);
                int counter =  this.messagesCounter.get(idx) + 1;
                this.messagesCounter.set(idx, counter);
                messageY += 30;

                if (this.messagesCounter.get(idx) > (this.gamePanel.FPS * 3)) {
                    this.messages.remove(idx);
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
        int frameX = this.gamePanel.tileSize;
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
        textY += lineSpacing;
        this.graphics2D.drawString("Coin", textX, textY);
        textY += lineSpacing + 10;
        this.graphics2D.drawString("Weapon", textX, textY);
        textY += lineSpacing + 15;
        this.graphics2D.drawString("Shield", textX, textY);

        // attribuet values
        int rightX = (frameX + frameWidth) - 30;
        textY = frameY + this.gamePanel.tileSize;

        String textValue = String.valueOf(this.gamePanel.player.currentLevel);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.currentHearts + "/" + this.gamePanel.player.maxHearts);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.currentMana + "/" + this.gamePanel.player.maxMana);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.strength);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.dexterity);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.attackDamage);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;
        textValue = String.valueOf(this.gamePanel.player.defenseArmor);
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
        textValue = String.valueOf(this.gamePanel.player.coins);
        textX = this.calcXPositionForAlignToRightText(textValue, rightX);
        this.graphics2D.drawString(textValue, textX, textY);
        textY += lineSpacing;

        this.graphics2D.drawImage(this.gamePanel.player.currentWeapon.down1, rightX - this.gamePanel.tileSize, textY - 24, null);
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawImage(this.gamePanel.player.currentShield.down1, rightX - this.gamePanel.tileSize, textY - 24, null);
    }

    private void drawPlayerInventory() {
        // inventory window frame
        int inventoryWindowFrameX = this.gamePanel.tileSize * 9;
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
            if (item == this.gamePanel.player.currentWeapon || item == this.gamePanel.player.currentShield) {
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
            String itemDescription = this.gamePanel.player.inventory.get(itemIndex).objectDescription;
            for (String line : itemDescription.split("\n")) {
                this.graphics2D.drawString(line, descriptionTextX, descriptionTextY);
                descriptionTextY += 32;
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
