package main;

import entity.Entity;
import enums.Direction;

public class CollisionDetector {
    private final GamePanel gamePanel;

    public CollisionDetector(GamePanel p) {
        this.gamePanel = p;
    }

    public void detectEntityCollisionWithTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX / this.gamePanel.tileSize;
        int entityRightColumn = entityLeftWorldX / this.gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / this.gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / this.gamePanel.tileSize;

        int tileNumber1 = 0, tileNumber2 = 0;
        switch (entity.direction) {
            case Direction.UP -> {
                entityTopRow = (entityTopWorldY - entity.velocity) / this.gamePanel.tileSize;
                tileNumber1 = this.gamePanel.tileManager.mapTileNumbers[entityLeftColumn][entityTopRow];
                tileNumber2 = this.gamePanel.tileManager.mapTileNumbers[entityRightColumn][entityTopRow];
            }
            case Direction.DOWN -> {
                entityBottomRow = (entityBottomWorldY + entity.velocity) / this.gamePanel.tileSize;
                tileNumber1 = this.gamePanel.tileManager.mapTileNumbers[entityLeftColumn][entityBottomRow];
                tileNumber2 = this.gamePanel.tileManager.mapTileNumbers[entityRightColumn][entityBottomRow];
            }
            case Direction.LEFT -> {
                entityLeftColumn = (entityLeftWorldX - entity.velocity) / this.gamePanel.tileSize;
                tileNumber1 = this.gamePanel.tileManager.mapTileNumbers[entityLeftColumn][entityTopRow];
                tileNumber2 = this.gamePanel.tileManager.mapTileNumbers[entityLeftColumn][entityBottomRow];
            }
            case Direction.RIGHT -> {
                entityRightColumn = (entityRightWorldX + entity.velocity) / this.gamePanel.tileSize;
                tileNumber1 = this.gamePanel.tileManager.mapTileNumbers[entityRightColumn][entityTopRow];
                tileNumber2 = this.gamePanel.tileManager.mapTileNumbers[entityRightColumn][entityBottomRow];
            }
        }
        if(this.gamePanel.tileManager.tile[tileNumber1].isSolid || this.gamePanel.tileManager.tile[tileNumber2].isSolid) {
            entity.collisionDetected = true;
        }
    }
}
