package main;

import enums.Direction;
import enums.GameState;

public class EventHandler {
    private final GamePanel gamePanel;
    private EventRectangle[][] eventRectangle;
    private int previousEventPositionX, previousEventPositionY;
    private boolean playerCanAccessEventAgain;

    public EventHandler(GamePanel p) {
        this.gamePanel = p;

        this.setDefaultValues();
    }

    private void setDefaultValues() {
        // setup world events
        this.eventRectangle = new EventRectangle[this.gamePanel.maxWorldColumns][this.gamePanel.maxWorldRows];
        for (int row = 0; row < this.gamePanel.maxWorldRows; row++) {
            for (int column = 0; column < this.gamePanel.maxWorldColumns; column++) {
                this.eventRectangle[row][column] = new EventRectangle();
                this.eventRectangle[row][column].x = (this.gamePanel.tileSize / 2) - 1;
                this.eventRectangle[row][column].y = (this.gamePanel.tileSize / 2) - 1;
                this.eventRectangle[row][column].width = 2;
                this.eventRectangle[row][column].height = 2;
                this.eventRectangle[row][column].eventRectangleDefaultX = this.eventRectangle[row][column].x;
                this.eventRectangle[row][column].eventRectangleDefaultY = this.eventRectangle[row][column].y;
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
        if (playerCanAccessEventAgain) {
            if (playerHitSomething(22, 18, Direction.UP)) { eventDamagePit(22, 18, GameState.DIALOGUE); }
            if (playerHitSomething(22, 17, Direction.UP)) { eventHealingPool(22, 17, GameState.DIALOGUE); }
        }
    }

    public boolean playerHitSomething(int eventColumn, int eventRow, Direction direction) {
        boolean hit = false;
        this.gamePanel.player.solidArea.x = this.gamePanel.player.worldX + this.gamePanel.player.solidAreaDefaultX;
        this.gamePanel.player.solidArea.y = this.gamePanel.player.worldY + this.gamePanel.player.solidAreaDefaultY;
        this.eventRectangle[eventRow][eventColumn].x = (eventColumn * this.gamePanel.tileSize) + this.eventRectangle[eventRow][eventColumn].eventRectangleDefaultX;
        this.eventRectangle[eventRow][eventColumn].y = (eventRow * this.gamePanel.tileSize) + this.eventRectangle[eventRow][eventColumn].eventRectangleDefaultY;

        if (this.gamePanel.player.solidArea.intersects(this.eventRectangle[eventRow][eventColumn])) {
            if (this.gamePanel.player.direction == direction || direction == Direction.ANY) {
                hit = true;
                this.previousEventPositionX = this.gamePanel.player.worldX;
                this.previousEventPositionY = this.gamePanel.player.worldY;
            }
        }
        this.gamePanel.player.solidArea.x = this.gamePanel.player.solidAreaDefaultX;
        this.gamePanel.player.solidArea.y = this.gamePanel.player.solidAreaDefaultY;
        this.eventRectangle[eventRow][eventColumn].x = this.eventRectangle[eventRow][eventColumn].eventRectangleDefaultX;
        this.eventRectangle[eventRow][eventColumn].y = this.eventRectangle[eventRow][eventColumn].eventRectangleDefaultY;

        return hit;
    }

    private void eventDamagePit(int eventColumn, int eventRow, GameState gameState) {
        this.gamePanel.gameState = gameState;
        this.gamePanel.gui.currentDialogueMessage = "You fall into a pit!";
        if (this.gamePanel.player.currentHearts > 0) {
            this.gamePanel.player.currentHearts--;
        }
        this.eventRectangle[eventRow][eventColumn].eventDone = true;
        this.playerCanAccessEventAgain = false;
    }

    private void eventHealingPool(int eventColumn, int eventRow, GameState gameState) {
        if (this.gamePanel.keyboard.isEnterPressed) {
            this.gamePanel.gameState = gameState;
            this.gamePanel.gui.currentDialogueMessage = "You drink the water!";
            this.gamePanel.player.currentHearts = this.gamePanel.player.maxHearts;
            this.eventRectangle[eventRow][eventColumn].eventDone = true;
        }
    }
}
