package main;

import entity.Entity;
import enums.Direction;
import object.GameObject;

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
        // if entity hits solid tile then collision detected
        if(this.gamePanel.tileManager.tiles.get(tileNumber1).isSolid || this.gamePanel.tileManager.tiles.get(tileNumber2).isSolid) {
            entity.collisionDetected = true;
        }
    }

    public int detectEntityCollisionWithObject(Entity entity, boolean player) {
        int objectIndex = Integer.MAX_VALUE;
        for(int idx = 0 ; idx < this.gamePanel.objects.size() ; idx++) {
            GameObject object = this.gamePanel.objects.get(idx);
            if(object != null) {
                // entity solid area coors
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // object solid area coors
                object.solidArea.x = object.worldX + object.solidArea.x;
                object.solidArea.y = object.worldY + object.solidArea.y;
                switch (entity.direction) {
                    case Direction.UP -> { entity.solidArea.y -= entity.velocity; }
                    case Direction.DOWN -> { entity.solidArea.y += entity.velocity; }
                    case Direction.LEFT -> { entity.solidArea.x -= entity.velocity; }
                    case Direction.RIGHT -> { entity.solidArea.x += entity.velocity; }
                }
                // check if entity area intersects with object area
                if(entity.solidArea.intersects(object.solidArea)) {
                    if(object.isSolid) { entity.collisionDetected = true; }
                    if(player) { objectIndex = idx; }
                }
                // reset areas
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                object.solidArea.x = object.solidAreaDefaultX;
                object.solidArea.y = object.solidAreaDefaultY;
            }
        }
        return objectIndex;
    }
}
