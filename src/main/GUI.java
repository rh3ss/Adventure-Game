package main;


import entity.Entity;
import entity.Player;
import enums.*;
import enums.Menu;
import object.GameObject;
import object.pickup.Coin;
import object.pickup.CoinPile;
import object.pickup.Heart;
import object.pickup.ManaCrystal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GUI {
    private final GamePanel gamePanel;
    private final Font fontMaruMonica;
    private final Color colorBlack, colorLightBlack, colorGunmetal, colorWhite, colorWhiteGoldLight, colorGold, colorTitleGold, colorGameOverRed;
    private final BufferedImage titleScreen, heartFull, heartBlank, coin, manaCrystalFull, manaCrystalBlank;
    private Graphics2D graphics2D;
    public Menu menuSelection;
    public OptionsState optionsState;
    public OptionsSelection optionsSelection;
    public GameOverSelection gameOverSelection;
    public ArrayList<String> messages;
    public ArrayList<Color> messagesColor;
    public ArrayList<Integer> messagesCounter;
    public Entity interactedNPC;
    public TradingState tradingState;
    public TradingSelection tradingSelection;
    public boolean messageOn, gameFinished, inventoryFull;
    public String currentDialogueMessage;
    public int playerInventorySlotColumnSelected, playerInventorySlotRowSelected;
    public int npcInventorySlotColumnSelected, npcInventorySlotRowSelected;
    public int transitionCounter;

    public GUI(GamePanel p) {
        this.gamePanel = p;

        InputStream is = getClass().getResourceAsStream("/res/font/x12y16pxMaruMonica.ttf");
        try {
            this.fontMaruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (FontFormatException | IOException e) { throw new RuntimeException(e);}

        this.colorBlack = new Color(0x000000);
        this.colorLightBlack = new Color(0x161616);
        this.colorGunmetal = new Color(0x2a2f35);
        this.colorWhite = new Color(0xffffff);
        this.colorWhiteGoldLight = new Color(0xfffff4);
        this.colorGold = new Color(0xf0be5a);
        this.colorTitleGold = new Color(0xe0aa3e);
        this.colorGameOverRed = new Color(255, 16, 16, 60);

        this.messages = new ArrayList<>();
        this.messagesColor = new ArrayList<>();
        this.messagesCounter = new ArrayList<>();
        this.messageOn = this.gameFinished = this.inventoryFull = false;

        this.menuSelection = Menu.NEW_GAME;
        this.optionsState = OptionsState.STATE_1;
        this.optionsSelection = OptionsSelection.SELECTED_1;
        this.gameOverSelection = GameOverSelection.RESPAWN;
        this.tradingState = TradingState.SELECT;
        this.tradingSelection = TradingSelection.BUY;

        BufferedImage titleImage = null;
        try { titleImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/titleScreen/title_screen_2.png"))); } catch (IOException _) {}
        this.titleScreen = titleImage;

        Entity heart = new Heart(this.gamePanel, -1, -1);
        this.heartFull = heart.image1;
        this.heartBlank = heart.image2;
        Entity coin = new CoinPile(this.gamePanel, -1, -1);
        this.coin = coin.down1;
        Entity manaCrystal = new ManaCrystal(this.gamePanel, -1, -1);
        this.manaCrystalFull = manaCrystal.image1;
        this.manaCrystalBlank = manaCrystal.image2;

        this.playerInventorySlotColumnSelected = this.playerInventorySlotRowSelected = 0;
        this.npcInventorySlotColumnSelected = this.npcInventorySlotRowSelected = 0;
        this.transitionCounter = 0;
    }

    public void addMessage(String message, Color color) {
        this.messages.add(message);
        this.messagesColor.add(color);
        this.messagesCounter.add(0);
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
                int counter = !this.inventoryFull ? this.messagesCounter.get(idx) + 1 : this.messagesCounter.get(idx) + 50;
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

    public void draw(Graphics2D g2) {
        this.graphics2D = g2;
        this.graphics2D.setFont(fontMaruMonica);
        this.graphics2D.setColor(Color.WHITE);

        switch (this.gamePanel.gameState) {
            case GameState.TITLE -> { this.drawTitleScreen(); }
            case GameState.PLAYING -> { this.drawPlayingScreen(); }
            case GameState.PAUSED -> { this.drawPausedScreen(); }
            case GameState.DIALOGUE -> { this.drawDialogueScreen(); }
            case GameState.INVENTORY -> { this.drawInventoryScreen(); }
            case GameState.OPTIONS -> { this.drawOptionsScreen(); }
            case GameState.TRANSITION -> { this.drawTransitionScreen(); }
            case GameState.TRADING ->  { this.drawTradingScreen(); }
            case GameState.GAME_OVER -> { this.drawGameOverScreen(); }
        }
    }

    private void drawTitleScreen() {
        this.graphics2D.drawImage(this.titleScreen, 0, 0, this.gamePanel.screenWidth, this.gamePanel.screenHeight, null);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 96F));

        String titleText = "Legends of";
        int xPos = this.gamePanel.tileSize;
        int yPos = this.gamePanel.tileSize * 3;
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.BOLD, 80F));
        this.graphics2D.setColor(this.colorGunmetal);
        this.graphics2D.drawString(titleText, xPos + 5, yPos + 5);
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.drawString(titleText, xPos, yPos);
        titleText = "Arcadia";
        yPos += (int) (this.gamePanel.tileSize * 1.5);
        this.graphics2D.setColor(this.colorGunmetal);
        this.graphics2D.drawString(titleText, xPos + 5, yPos + 5);
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.drawString(titleText, xPos, yPos);
        // menu values
        String[] menuTexts = {"New Game", "Load Game", "Quit"};
        Menu[] menuValues = {Menu.NEW_GAME, Menu.LOAD_GAME, Menu.QUIT};
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.BOLD, 48F));
        yPos += this.gamePanel.tileSize * 7;
        for (int i = 0; i < menuTexts.length; i++) {
            if (this.menuSelection == menuValues[i]) {
                this.graphics2D.setColor(this.colorTitleGold);
            } else {
                this.graphics2D.setColor(this.colorWhite);
            }
            this.graphics2D.drawString(menuTexts[i], xPos, yPos);
            yPos += this.gamePanel.tileSize;
        }
    }

    private void drawPlayingScreen() {
        this.drawPlayerHearts();
        this.drawPlayerMana();
        this.drawPlayerCoins();
        this.drawPlayerEquipment();
        this.drawMessages();
        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(30F));
        this.graphics2D.drawString("Column: " + (this.gamePanel.player.worldX / this.gamePanel.tileSize), this.gamePanel.tileSize, this.gamePanel.tileSize * 8);
        this.graphics2D.drawString("Row: " + ((this.gamePanel.player.worldY / this.gamePanel.tileSize) + 1), this.gamePanel.tileSize, this.gamePanel.tileSize * 8 + 30);
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
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.BOLD, 40F));
        FontMetrics fontMetrics = this.graphics2D.getFontMetrics();
        // current coins value
        String textValueOfCoins = String.valueOf(this.gamePanel.player.coins);
        int textX = this.calcXPositionForAlignToRightText(textValueOfCoins, xPos);
        int textY = yPos + (this.coin.getHeight() - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
        // coin image
        this.graphics2D.drawImage(this.coin, xPos, yPos, null);
        // text shadow
        this.graphics2D.setColor(Color.BLACK);
        this.graphics2D.drawString(textValueOfCoins, textX + 2, textY + 2);
        // text
        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.drawString(textValueOfCoins, textX, textY);
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

    private void drawPausedScreen() {
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 100F));

        String pausedText = "PAUSED";
        int xPos = this.calcXPositionForCenteredText(pausedText);
        int yPos = this.calcYPositionForCenteredText(pausedText);
        this.graphics2D.setColor(this.colorBlack);
        this.graphics2D.drawString(pausedText, xPos + 5, yPos + 5);
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.drawString(pausedText, xPos, yPos);
    }

    private void drawDialogueScreen() {
        // basic window
        int windowWidth = this.gamePanel.tileSize * 15;
        int windowHeight = this.gamePanel.tileSize * 4;
        int windowX = (this.gamePanel.screenWidth - windowWidth) / 2;
        int windowY = this.gamePanel.tileSize * 2;
        this.drawSubWindowScreen(windowX, windowY, windowWidth, windowHeight, this.colorBlack, this.colorGold);
        // draw text
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 28F));
        windowX += this.gamePanel.tileSize;
        windowY += this.gamePanel.tileSize;
        for (String line : this.currentDialogueMessage.split("\n")) {
            this.graphics2D.drawString(line, windowX, windowY);
            windowY += 40;
        }
    }

    private void drawInventoryScreen() {
        // basic window
        int windowWidth = this.gamePanel.tileSize * 15;
        int windowHeight = this.gamePanel.tileSize * 8;
        int windowX = (this.gamePanel.screenWidth - windowWidth) / 2;
        int windowY = (this.gamePanel.screenHeight - windowHeight) / 2;
        this.drawSubWindowScreen(windowX, windowY, windowWidth, windowHeight, this.colorBlack, this.colorGold);
        // attributes part
        int attributesAreaX = windowX + (this.gamePanel.tileSize * (this.gamePanel.player.inventoryColumnSize + 1));
        int attributesAreaY = windowY + (this.gamePanel.tileSize / 2);
        int attributesAreaWidth = this.gamePanel.tileSize * 4;
        int attributesAreaHeight = windowHeight - this.gamePanel.tileSize;
        this.drawPlayerAttributes(attributesAreaX, attributesAreaY, attributesAreaWidth, attributesAreaHeight);
        // inventory part
        int inventoryAreaX = windowX + (this.gamePanel.tileSize / 2);
        int inventoryAreaY = windowY + (this.gamePanel.tileSize / 2);
        this.drawPlayerInventory(inventoryAreaX, inventoryAreaY);
    }
    private void drawPlayerInventory(int startX, int startY) {
        String inventoryText = "Inventory";
        int inventoryTextX = this.calcXPositionForCenteredText(inventoryText);
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(46F));
        this.graphics2D.drawString(inventoryText, inventoryTextX, startY + this.gamePanel.tileSize);
        // player image
        int playerPosX = startX + (this.gamePanel.tileSize * 3) + (this.gamePanel.tileSize / 2);
        int playerPosY = startY + this.gamePanel.tileSize;
        this.graphics2D.drawImage(this.gamePanel.player.down1, playerPosX, playerPosY, this.gamePanel.tileSize * 2, this.gamePanel.tileSize * 2, null);
        // draw all inventory slots
        int inventorySlotStartX = startX;
        int inventorySlotStartY = startY + (this.gamePanel.tileSize * 4);
        for(int row = 0; row < this.gamePanel.player.inventoryRowSize; row++) {
            for(int col = 0; col < this.gamePanel.player.inventoryColumnSize; col++) {
                drawInventorySlot(inventorySlotStartX + (col * this.gamePanel.tileSize), inventorySlotStartY + (row * this.gamePanel.tileSize), false);
            }
        }
        // draw all game objects
        for(int itemIndex = 0; itemIndex < this.gamePanel.player.inventory.size(); itemIndex++) {
            GameObject object = this.gamePanel.player.inventory.get(itemIndex);
            int col = itemIndex % this.gamePanel.player.inventoryColumnSize;
            int row = itemIndex / this.gamePanel.player.inventoryColumnSize;
            int slotX = inventorySlotStartX + (col * this.gamePanel.tileSize);
            int slotY = inventorySlotStartY + (row * this.gamePanel.tileSize);
            // highlight, player equipped objects
            // ERROR TO DO: hier muss als highligth das genauer object sein nicht vom selben typ
            boolean equipped = object == this.gamePanel.player.currentWeapon ||
                    object == this.gamePanel.player.currentShield ||
                    object == this.gamePanel.player.currentArmor ||
                    object == this.gamePanel.player.currentLight;
            drawInventorySlot(slotX, slotY, equipped);
            this.graphics2D.drawImage(object.down1, slotX, slotY, null);
            // draw stackable number
            if(object.objectCurrentStackableAmount > 1) {
                String amount = String.valueOf(object.objectCurrentStackableAmount);
                this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(24F));
                int amountX = this.calcXPositionForAlignToRightText(amount, slotX + this.gamePanel.tileSize - 4);
                int amountY = slotY + this.gamePanel.tileSize - 4;
                this.graphics2D.setColor(this.colorBlack);
                this.graphics2D.drawString(amount, amountX + 2, amountY + 2);
                this.graphics2D.setColor(this.colorWhite);
                this.graphics2D.drawString(amount, amountX, amountY);
            }
        }
        // inventory selected cursor
        int inventoryCursorX = inventorySlotStartX + (this.gamePanel.tileSize * this.playerInventorySlotColumnSelected);
        int inventoryCursorY = inventorySlotStartY + (this.gamePanel.tileSize * this.playerInventorySlotRowSelected);
        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.setStroke(new BasicStroke(3));
        this.graphics2D.drawRoundRect(inventoryCursorX, inventoryCursorY, this.gamePanel.tileSize, this.gamePanel.tileSize, 10, 10);
        // game object description
        int selectedIndex = this.getSelectedInventoryItemIndexOnSlot(this.playerInventorySlotColumnSelected, this.playerInventorySlotRowSelected);
        if(selectedIndex < this.gamePanel.player.inventory.size()) {
            GameObject selectedObject = this.gamePanel.player.inventory.get(selectedIndex);
            if(selectedObject.objectDescription != null && !selectedObject.objectDescription.isBlank()) {
                int tooltipX = inventoryCursorX + this.gamePanel.tileSize + 10;
                int tooltipY = inventoryCursorY - 10;
                String[] lines = selectedObject.objectDescription.split("\n");
                int tooltipHeight = (lines.length * 28) + 20;
                int tooltipWidth = 350;
                this.drawSubWindowScreen(tooltipX, tooltipY, tooltipWidth, tooltipHeight, this.colorBlack, this.colorGold);
                this.graphics2D.setColor(this.colorWhite);
                this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(28F));
                int textX = tooltipX + 12;
                int textY = tooltipY + 32;
                for(String line : lines) {
                    this.graphics2D.drawString(line, textX, textY);
                    textY += 28;
                }
            }
        }
    }
    private void drawInventorySlot(int slotX, int slotY, boolean equipped) {
        // slot color
        Color color = (equipped) ? this.colorGold : this.colorBlack;
        this.graphics2D.setColor(color);
        this.graphics2D.fillRect(slotX, slotY, this.gamePanel.tileSize, this.gamePanel.tileSize);
        // slot shadow
        this.graphics2D.setColor(this.colorGold);
        this.graphics2D.setStroke(new BasicStroke(2));
        this.graphics2D.drawLine(slotX, slotY, slotX + this.gamePanel.tileSize, slotY);
        this.graphics2D.drawLine(slotX, slotY + this.gamePanel.tileSize, slotX + this.gamePanel.tileSize, slotY + this.gamePanel.tileSize);
        this.graphics2D.drawLine(slotX, slotY, slotX, slotY + this.gamePanel.tileSize);
        this.graphics2D.drawLine(slotX + this.gamePanel.tileSize, slotY, slotX + this.gamePanel.tileSize, slotY + this.gamePanel.tileSize);
    }
    private void drawPlayerAttributes(int frameX, int frameY, int frameWidth, int frameHeight) {
        String[] names = {"Level", "Hearts", "Mana", "Strength", "Dexterity", "Attack", "Defense"};
        String[] values = {
                String.valueOf(this.gamePanel.player.currentLevel),
                this.gamePanel.player.currentHearts + "/" + this.gamePanel.player.maxHearts,
                this.gamePanel.player.currentMana + "/" + this.gamePanel.player.maxMana,
                String.valueOf(Math.round(this.gamePanel.player.strength * 100.0) / 100.0),
                String.valueOf(Math.round(this.gamePanel.player.dexterity * 100.0) / 100.0),
                "+" + Math.round(this.gamePanel.player.attackDamage * 100) + "%",
                "+" + Math.round(this.gamePanel.player.defenseArmor * 100) + "%"
        };
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(32F));
        int lineSpacing = 35;
        int textX = frameX;
        int textY = (frameY + this.gamePanel.tileSize * 2);
        int valueRightX = frameX + frameWidth - 10;
        for(int idx = 0; idx < names.length; idx++) {
            this.graphics2D.drawString(names[idx], textX, textY);
            int valueX = this.calcXPositionForAlignToRightText(values[idx], valueRightX);
            this.graphics2D.drawString(values[idx], valueX, textY);
            textY += lineSpacing;
        }
    }

    private void drawEntityInventory(Entity entity, boolean showCursor) {
        int inventoryWindowFrameX, inventoryWindowFrameY;
        int inventoryWindowFrameWidth, inventoryWindowFrameHeight;
        int inventorySlotColumnSelected, inventorySlotRowSelected;
        
        if (entity instanceof Player) {
            inventoryWindowFrameX = (this.gamePanel.screenWidth / 2) + this.gamePanel.tileSize * 2;
            inventoryWindowFrameY = this.gamePanel.tileSize;
            inventoryWindowFrameWidth = this.gamePanel.tileSize * (this.gamePanel.player.inventoryColumnSize + 1);
            inventoryWindowFrameHeight = this.gamePanel.tileSize * (this.gamePanel.player.inventoryRowSize + 1);
            inventorySlotColumnSelected = this.playerInventorySlotColumnSelected;
            inventorySlotRowSelected = this.playerInventorySlotRowSelected;
        }
        else {
            inventoryWindowFrameX = this.gamePanel.tileSize * 2;
            inventoryWindowFrameY = this.gamePanel.tileSize;
            inventoryWindowFrameWidth = this.gamePanel.tileSize * (entity.inventoryColumnSize + 1);
            inventoryWindowFrameHeight = this.gamePanel.tileSize * (entity.inventoryRowSize + 1);
            inventorySlotColumnSelected = this.npcInventorySlotColumnSelected;
            inventorySlotRowSelected = this.npcInventorySlotRowSelected;
        }
        // inventory window frame
        this.drawSubWindowScreen(inventoryWindowFrameX, inventoryWindowFrameY, inventoryWindowFrameWidth, inventoryWindowFrameHeight, this.colorBlack, this.colorGold);
        
        // players inventory objects
        int inventorySlotStartX = inventoryWindowFrameX + 20;
        int inventorySlotStartY = inventoryWindowFrameY + 20;
        int inventorySlotX = inventorySlotStartX;
        int inventorySlotY = inventorySlotStartY;
        for (int idx = 1; idx < entity.inventory.size() + 1; idx++) {
            GameObject object = entity.inventory.get(idx - 1);
            // highlight players equipped items
            if (object == entity.currentWeapon ||
                    object == entity.currentShield ||
                    object == entity.currentArmor ||
                    object == entity.currentLight
            ) {
                this.graphics2D.setColor(this.colorGold);
                this.graphics2D.fillRoundRect(inventorySlotX, inventorySlotY, this.gamePanel.tileSize, this.gamePanel.tileSize, 10, 10);
            }
            this.graphics2D.drawImage(object.down1, inventorySlotX, inventorySlotY, null);
            // object amount
            if (entity instanceof Player && object.objectCurrentStackableAmount > 1) {
                this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(32F));
                String textAmount = String.valueOf(object.objectCurrentStackableAmount);
                int amountX = this.calcXPositionForAlignToRightText(textAmount, inventorySlotX + 44);
                int amountY = inventorySlotY + this.gamePanel.tileSize;
                this.graphics2D.setColor(Color.GRAY);
                this.graphics2D.drawString(textAmount, amountX, amountY);
                this.graphics2D.setColor(Color.WHITE);
                this.graphics2D.drawString(textAmount, amountX - 3, amountY - 3);

            }
            inventorySlotX += this.gamePanel.tileSize;
            if (idx % entity.inventoryColumnSize == 0) {
                inventorySlotX = inventorySlotStartX;
                inventorySlotY += this.gamePanel.tileSize;
            }
        }

        // only if cursor is necessary
        if (showCursor) {
            // inventory selected cursor
            int inventoryCursorX = inventorySlotStartX + (this.gamePanel.tileSize * inventorySlotColumnSelected);
            int inventoryCursorY = inventorySlotStartY + (this.gamePanel.tileSize * inventorySlotRowSelected);
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
            int itemIndex = this.getSelectedInventoryItemIndexOnSlot(inventorySlotColumnSelected, inventorySlotRowSelected);
            if (itemIndex < entity.inventory.size()) {
                this.drawSubWindowScreen(descriptionFrameX, descriptionFrameY, descriptionFrameWidth, descriptionFrameHeight, this.colorBlack, this.colorGold);
                GameObject object = entity.inventory.get(itemIndex);
                String objectDescription = object.objectDescription;
                for (String line : objectDescription.split("\n")) {
                    this.graphics2D.drawString(line, descriptionTextX, descriptionTextY);
                    descriptionTextY += 32;
                }
            }
        }
    }

    private void drawOptionsScreen() {
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(32F));

        int optionsWindowFrameWidth = this.gamePanel.tileSize * 8;
        int optionsWindowFrameHeight = this.gamePanel.tileSize * 10;
        int optionsWindowFrameX = (this.gamePanel.screenWidth - optionsWindowFrameWidth) / 2;
        int optionsWindowFrameY = this.gamePanel.tileSize * 2;
        this.drawSubWindowScreen(optionsWindowFrameX, optionsWindowFrameY, optionsWindowFrameWidth, optionsWindowFrameHeight, this.colorBlack, this.colorGold);

        switch (this.optionsState) {
            case OptionsState.STATE_1 -> { this.optionsTop(optionsWindowFrameX, optionsWindowFrameY); }
            case OptionsState.STATE_2 -> { this.optionsFullScreenNotification(optionsWindowFrameX, optionsWindowFrameY); }
            case OptionsState.STATE_3 -> { this.optionsControls(optionsWindowFrameX, optionsWindowFrameY); }
            case OptionsState.STATE_4 -> { this.optionsEndGameConfirmation(optionsWindowFrameX, optionsWindowFrameY); }
        }

        this.gamePanel.keyboard.isEnterPressed = false;
    }
    private void optionsTop(int optionsWindowFrameX, int optionsWindowFrameY) {
        this.graphics2D.setColor(this.colorWhite);
        String optionsText = "Options";
        int textX = this.calcXPositionForCenteredText(optionsText);
        int textY = optionsWindowFrameY + this.gamePanel.tileSize;
        this.graphics2D.drawString(optionsText, textX, textY);

        textX = optionsWindowFrameX + this.gamePanel.tileSize;
        // full screen
        textY += this.gamePanel.tileSize * 2;
        this.graphics2D.drawString("Full Screen", textX, textY);
        if (this.optionsSelection == OptionsSelection.SELECTED_1) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.gamePanel.fullScreenOn = !this.gamePanel.fullScreenOn;
                this.optionsState = OptionsState.STATE_2;
            }
        }
        // control
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("Control", textX, textY);
        if (this.optionsSelection == OptionsSelection.SELECTED_2) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_3;
                this.optionsSelection = OptionsSelection.SELECTED_1;
            }
        }
        // end game
        textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("End Game", textX, textY);
        if (this.optionsSelection == OptionsSelection.SELECTED_3) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_4;
                this.optionsSelection = OptionsSelection.SELECTED_1;
            }
        }
        // back
        textY += this.gamePanel.tileSize * 4;
        this.graphics2D.drawString("Back", textX, textY);
        if (this.optionsSelection == OptionsSelection.SELECTED_4) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.gamePanel.gameState = GameState.PLAYING;
                this.optionsSelection = OptionsSelection.SELECTED_1;
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
        this.graphics2D.setColor(this.colorWhite);
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
        if (this.optionsSelection == OptionsSelection.SELECTED_1) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_1;
            }
        }
    }
    private void optionsControls(int optionsWindowFrameX, int optionsWindowFrameY) {
        this.graphics2D.setColor(this.colorWhite);
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
        this.graphics2D.drawString("E", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("P", textX, textY); textY += this.gamePanel.tileSize;
        this.graphics2D.drawString("ESC", textX, textY);

        // back
        textX = optionsWindowFrameX + this.gamePanel.tileSize;
        textY = optionsWindowFrameY + (this.gamePanel.tileSize * 9);
        this.graphics2D.drawString("Back", textX, textY);
        if (this.optionsSelection == OptionsSelection.SELECTED_1) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_1;
                this.optionsSelection = OptionsSelection.SELECTED_2;
            }
        }

    }
    private void optionsEndGameConfirmation(int optionsWindowFrameX, int optionsWindowFrameY) {
        this.graphics2D.setColor(this.colorWhite);
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
        if (this.optionsSelection == OptionsSelection.SELECTED_1) {
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
        if (this.optionsSelection == OptionsSelection.SELECTED_2) {
            this.graphics2D.drawString(">", textX - 25, textY);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.optionsState = OptionsState.STATE_1;
                this.optionsSelection = OptionsSelection.SELECTED_3;
            }
        }
    }

    private void drawTransitionScreen() {
        this.transitionCounter++;
        this.graphics2D.setColor(new Color(0, 0, 0, this.transitionCounter * 5));
        this.graphics2D.fillRect(0, 0, this.gamePanel.screenWidth, this.gamePanel.screenHeight);
        // transition is full black, now teleport player
        if (this.transitionCounter >= 50) {
            this.gamePanel.eventHandler.setPlayerToTeleportedDestination();
            this.transitionCounter = 0;
        }
    }

    private void drawTradingScreen() {
        switch (this.tradingState) {
            case TradingState.SELECT -> { this.drawTradingSelect(); }
            case TradingState.BUY -> { this.drawTradingBuy(); }
            case TradingState.SELL -> { this.drawTradingSell(); }
        }
        this.gamePanel.keyboard.isEnterPressed = false;
    }
    private void drawTradingSelect() {
        this.drawDialogueScreen();
        // draw trading options
        int x = this.gamePanel.tileSize * 17;
        int y = this.gamePanel.tileSize * 3;
        // draw text
        this.graphics2D.drawString("Buy", x, y);
        if (this.tradingSelection == TradingSelection.BUY) {
            this.graphics2D.drawString(">", x - 24, y);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.tradingState = TradingState.BUY;
            }
        }
        y += this.gamePanel.tileSize;
        this.graphics2D.drawString("Sell", x, y);
        if (this.tradingSelection == TradingSelection.SELL) {
            this.graphics2D.drawString(">", x - 24, y);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.tradingState = TradingState.SELL;
            }
        }
        y += this.gamePanel.tileSize;
        this.graphics2D.drawString("Leave", x, y);
        if (this.tradingSelection == TradingSelection.LEAVE) {
            this.graphics2D.drawString(">", x - 24, y);
            if (this.gamePanel.keyboard.isEnterPressed) {
                this.tradingState = TradingState.SELECT;
                this.tradingSelection = TradingSelection.BUY;
                this.gamePanel.gameState = GameState.DIALOGUE;
                this.currentDialogueMessage = "I'm sure we'll see each other again soon. \nI wish you all the best on your journey.";
            }
        }
    }
    private void drawTradingBuy() {
        // draw player and trader inventory
        this.drawEntityInventory(this.gamePanel.player, false);
        this.drawEntityInventory(this.interactedNPC, true);
        // draw trading hint
        int x = this.gamePanel.tileSize * 2;
        int y = this.gamePanel.tileSize * 9;
        int width = this.gamePanel.tileSize * 6;
        int height = this.gamePanel.tileSize * 2;
        this.drawSubWindowScreen(x, y, width, height, this.colorBlack, this.colorGold);
        this.graphics2D.drawString("[ESC] Back", x + 24, y + 60);
        // draw player coins
        x = this.gamePanel.tileSize * 12;
        y = this.gamePanel.tileSize * 9;
        width = this.gamePanel.tileSize * 6;
        height = this.gamePanel.tileSize * 2;
        this.drawSubWindowScreen(x, y, width, height, this.colorBlack, this.colorGold);
        this.graphics2D.drawString("Coins: " + this.gamePanel.player.coins, x + 24, y + 60);
        // draw object price
        int itemIndex = this.getSelectedInventoryItemIndexOnSlot(this.npcInventorySlotColumnSelected, this.npcInventorySlotRowSelected);
        if (itemIndex < this.interactedNPC.inventory.size()) {
            GameObject selectedItem = this.interactedNPC.inventory.get(itemIndex);
            if (selectedItem.entityType == EntityType.OBJECT) {
                x = (int) (this.gamePanel.tileSize * 5.5);
                y = (int) (this.gamePanel.tileSize * 5.5);
                width = (int) (this.gamePanel.tileSize * 2.5);
                height = this.gamePanel.tileSize;
                this.drawSubWindowScreen(x, y, width, height, this.colorBlack, this.colorGold);
                this.graphics2D.drawImage(this.coin, x + 10, y + 8, 32, 32, null);
                int price = selectedItem.objectCoinValue;
                String priceText = String.valueOf(price);
                x = this.calcXPositionForAlignToRightText(priceText, this.gamePanel.tileSize * 8);
                this.graphics2D.drawString(priceText, x - 20, y + 32);

                // buy object
                if (this.gamePanel.keyboard.isEnterPressed) {
                    if (price > this.gamePanel.player.coins) {
                        this.tradingState = TradingState.SELECT;
                        this.gamePanel.gameState = GameState.DIALOGUE;
                        this.currentDialogueMessage = "You need more coins to buy that!";
                        this.drawDialogueScreen();
                    }
                    else {
                        if (this.gamePanel.player.playerCanObtainObjectInInventory(selectedItem)) {
                            this.gamePanel.player.coins -= price;
                        }
                        else {
                            this.tradingState = TradingState.SELECT;
                            this.gamePanel.gameState = GameState.DIALOGUE;
                            this.currentDialogueMessage = "Your inventory is full!";
                            this.drawDialogueScreen();
                        }
                    }
                }
            }
        }
    }
    private void drawTradingSell() {
        // draw player inventory
        this.drawEntityInventory(this.gamePanel.player, true);
        // draw trading hint
        int x = this.gamePanel.tileSize * 2;
        int y = this.gamePanel.tileSize * 9;
        int width = this.gamePanel.tileSize * 6;
        int height = this.gamePanel.tileSize * 2;
        this.drawSubWindowScreen(x, y, width, height, this.colorBlack, this.colorGold);
        this.graphics2D.drawString("[ESC] Back", x + 24, y + 60);
        // draw player coins
        x = this.gamePanel.tileSize * 12;
        y = this.gamePanel.tileSize * 9;
        width = this.gamePanel.tileSize * 6;
        height = this.gamePanel.tileSize * 2;
        this.drawSubWindowScreen(x, y, width, height, this.colorBlack, this.colorGold);
        this.graphics2D.drawString("Coins: " + this.gamePanel.player.coins, x + 24, y + 60);
        // draw object price
        int itemIndex = this.getSelectedInventoryItemIndexOnSlot(this.playerInventorySlotColumnSelected, this.playerInventorySlotRowSelected);
        if (itemIndex < this.gamePanel.player.inventory.size()) {
            GameObject selectedItem = this.gamePanel.player.inventory.get(itemIndex);
            if (selectedItem.entityType == EntityType.OBJECT) {
                x = (int) (this.gamePanel.tileSize * 15.5);
                y = (int) (this.gamePanel.tileSize * 5.5);
                width = (int) (this.gamePanel.tileSize * 2.5);
                height = this.gamePanel.tileSize;
                this.drawSubWindowScreen(x, y, width, height, this.colorBlack, this.colorGold);
                this.graphics2D.drawImage(this.coin, x + 10, y + 8, 32, 32, null);
                int price = selectedItem.objectCoinValue;
                String priceText = String.valueOf(price);
                x = this.calcXPositionForAlignToRightText(priceText, this.gamePanel.tileSize * 18);
                this.graphics2D.drawString(priceText, x - 20, y + 32);

                // sell object
                if (this.gamePanel.keyboard.isEnterPressed) {
                    if (selectedItem == this.gamePanel.player.currentWeapon ||
                            selectedItem == this.gamePanel.player.currentShield ||
                            selectedItem == this.gamePanel.player.currentArmor) {
                        this.tradingState = TradingState.SELECT;
                        this.gamePanel.gameState = GameState.DIALOGUE;
                        this.currentDialogueMessage = "You cannot sell equipped items!";
                        this.drawDialogueScreen();
                    }
                    else {
                        if (selectedItem.objectCurrentStackableAmount > 1) {
                            selectedItem.objectCurrentStackableAmount--;
                        }
                        else {
                            this.gamePanel.player.inventory.remove(selectedItem);
                        }
                        this.gamePanel.player.coins += price;
                    }
                }
            }
        }
    }

    private void drawGameOverScreen() {
        this.graphics2D.setColor(this.colorGameOverRed);
        this.graphics2D.fillRect(0, 0, this.gamePanel.screenWidth, this.gamePanel.screenHeight);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(110f));

        String text = "GAME OVER";
        int textX = this.calcXPositionForCenteredText(text);
        int textY = this.gamePanel.tileSize * 4;
        this.graphics2D.setColor(this.colorGunmetal);
        this.graphics2D.drawString(text, textX + 5, textY + 5);
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.drawString(text, textX, textY);

        // respawn
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(50f));
        text = "Respawn";
        textX = this.calcXPositionForCenteredText(text);
        textY += this.gamePanel.tileSize * 3;
        this.graphics2D.setColor(this.colorGunmetal);
        this.graphics2D.drawString(text, textX + 5, textY + 5);
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.drawString(text, textX, textY);
        if (this.gameOverSelection == GameOverSelection.RESPAWN) {
            this.graphics2D.drawString(">", textX - 40, textY);
        }

        // back to main menu
        text = "Quit";
        textX = this.calcXPositionForCenteredText(text);
        textY += this.gamePanel.tileSize;
        this.graphics2D.setColor(this.colorGunmetal);
        this.graphics2D.drawString(text, textX + 5, textY + 5);
        this.graphics2D.setColor(this.colorWhite);
        this.graphics2D.drawString(text, textX, textY);
        if (this.gameOverSelection == GameOverSelection.QUIT) {
            this.graphics2D.drawString(">", textX - 40, textY);
        }
    }

    public int getSelectedInventoryItemIndexOnSlot(int slotColumnSelected, int slotRowSelected) {
        return slotColumnSelected + (slotRowSelected * this.gamePanel.player.inventoryColumnSize);
    }

    private void drawSubWindowScreen(int x, int y, int width, int height, Color background, Color border) {
        int arcSize = 35;
        this.graphics2D.setColor(background);
        this.graphics2D.fillRoundRect(x, y, width, height, arcSize, arcSize);
        this.graphics2D.setColor(border);
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
