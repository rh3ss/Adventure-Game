package main;

import enums.Direction;
import enums.GameState;

public class EventHandler {
    private final GamePanel gamePanel;
    private EventRectangle[][][] eventRectangle;
    private boolean playerCanAccessEventAgain;
    public int previousEventPositionX, previousEventPositionY;
    public int tempMapNumber, tempColumn, tempRow;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        this.setDefaultValues();
    }

    private void setDefaultValues() {
        // setup world events
        this.eventRectangle = new EventRectangle[this.gamePanel.maxNumberOfMaps][this.gamePanel.maxWorldColumns][this.gamePanel.maxWorldRows];
        for (int map = 0; map < this.gamePanel.maxNumberOfMaps; map++) {
            for (int row = 0; row < this.gamePanel.maxWorldRows; row++) {
                for (int column = 0; column < this.gamePanel.maxWorldColumns; column++) {
                    this.eventRectangle[map][row][column] = new EventRectangle();
                    this.eventRectangle[map][row][column].x = (this.gamePanel.tileSize / 2) - 1;
                    this.eventRectangle[map][row][column].y = (this.gamePanel.tileSize / 2) - 1;
                    this.eventRectangle[map][row][column].width = 2;
                    this.eventRectangle[map][row][column].height = 2;
                    this.eventRectangle[map][row][column].eventRectangleDefaultX = this.eventRectangle[map][row][column].x;
                    this.eventRectangle[map][row][column].eventRectangleDefaultY = this.eventRectangle[map][row][column].y;
                }
            }
        }
        // set values
        this.previousEventPositionX = this.previousEventPositionY = 0;
        this.playerCanAccessEventAgain = true;
    }

    public void checkEvent() {
        // check if player is at least one 1 tile away from previous event
        int xDelta = Math.abs(this.gamePanel.player.worldX - this.previousEventPositionX);
        int yDelta = Math.abs(this.gamePanel.player.worldY - this.previousEventPositionY);
        if (Math.max(xDelta, yDelta) > this.gamePanel.tileSize) {
            this.playerCanAccessEventAgain = true;
        }
        if (this.playerCanAccessEventAgain) {
            if (playerHitSomething(0, 22, 18, Direction.UP)) { eventDamagePit(GameState.DIALOGUE); }
            else if (playerHitSomething(0, 22, 17, Direction.UP)) { eventHealingPool(GameState.DIALOGUE); }
            else if (playerHitSomething(0, 24, 21, Direction.ANY)) {
                eventTeleportPlayerIsTriggered(1, 12, 13);
            }
            else if (playerHitSomething(1, 12, 13, Direction.ANY)) {
                eventTeleportPlayerIsTriggered(0, 24, 21);
            }
        }
    }

    public boolean playerHitSomething(int mapNumber, int eventColumn, int eventRow, Direction direction) {
        boolean hit = false;
        if (mapNumber == this.gamePanel.currentMapNumber) {
            this.gamePanel.player.solidArea.x = this.gamePanel.player.worldX + this.gamePanel.player.solidAreaDefaultX;
            this.gamePanel.player.solidArea.y = this.gamePanel.player.worldY + this.gamePanel.player.solidAreaDefaultY;
            this.eventRectangle[mapNumber][eventRow][eventColumn].x = (eventColumn * this.gamePanel.tileSize) + this.eventRectangle[mapNumber][eventRow][eventColumn].eventRectangleDefaultX;
            this.eventRectangle[mapNumber][eventRow][eventColumn].y = (eventRow * this.gamePanel.tileSize) + this.eventRectangle[mapNumber][eventRow][eventColumn].eventRectangleDefaultY;

            if (this.gamePanel.player.solidArea.intersects(this.eventRectangle[mapNumber][eventRow][eventColumn])) {
                if (this.gamePanel.player.direction == direction || direction == Direction.ANY) {
                    hit = true;
                    this.previousEventPositionX = this.gamePanel.player.worldX;
                    this.previousEventPositionY = this.gamePanel.player.worldY;
                }
            }
            this.gamePanel.player.solidArea.x = this.gamePanel.player.solidAreaDefaultX;
            this.gamePanel.player.solidArea.y = this.gamePanel.player.solidAreaDefaultY;
            this.eventRectangle[mapNumber][eventRow][eventColumn].x = this.eventRectangle[mapNumber][eventRow][eventColumn].eventRectangleDefaultX;
            this.eventRectangle[mapNumber][eventRow][eventColumn].y = this.eventRectangle[mapNumber][eventRow][eventColumn].eventRectangleDefaultY;
        }
        return hit;
    }

    private void eventDamagePit(GameState gameState) {
        this.gamePanel.gameState = gameState;
        this.gamePanel.gui.currentDialogueMessage = "You fall into a pit!";
        if (this.gamePanel.player.currentHearts > 0) {
            this.gamePanel.player.currentHearts--;
        }
        // this.eventRectangle[eventRow][eventColumn].eventDone = true;
        this.playerCanAccessEventAgain = false;
    }

    private void eventHealingPool(GameState gameState) {
        if (this.gamePanel.keyboard.isEnterPressed) {
            this.gamePanel.gameState = gameState;
            this.gamePanel.gui.currentDialogueMessage = "You drink the water and mana!";
            this.gamePanel.player.currentHearts = this.gamePanel.player.maxHearts;
            this.gamePanel.player.currentMana = this.gamePanel.player.maxMana;
            //this.eventRectangle[eventRow][eventColumn].eventDone = true;
        }
    }

    private void eventTeleportPlayerIsTriggered(int mapNumber, int destinationColumn, int destinationRow) {
        this.gamePanel.gameState = GameState.TRANSITION;
        this.tempMapNumber = mapNumber;
        this.tempColumn = destinationColumn;
        this.tempRow = destinationRow;
        this.playerCanAccessEventAgain = false;
    }

    public void setPlayerToTeleportedDestination() {
        this.gamePanel.gameState = GameState.PLAYING;
        this.gamePanel.currentMapNumber = this.tempMapNumber;
        this.gamePanel.player.worldX = this.gamePanel.tileSize * this.tempColumn;
        this.gamePanel.player.worldY = this.gamePanel.tileSize * this.tempRow;
        this.previousEventPositionX = this.gamePanel.player.worldX;
        this.previousEventPositionY = this.gamePanel.player.worldY;
        // place player in new map
        this.gamePanel.assetSetter.setPlayer();
    }
}
