package main;

import entity.Entity;
import enums.Direction;
import enums.EntityType;

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
        for (int idx = 0; idx < this.gamePanel.entities.size(); idx++) {
            Entity object = this.gamePanel.entities.get(idx);
            if (object != null && object != entity && object.entityType == EntityType.OBJECT) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                object.solidArea.x = object.worldX + object.solidArea.x;
                object.solidArea.y = object.worldY + object.solidArea.y;
                switch (entity.direction) {
                    case Direction.UP -> { entity.solidArea.y -= entity.velocity; }
                    case Direction.DOWN -> { entity.solidArea.y += entity.velocity; }
                    case Direction.LEFT -> { entity.solidArea.x -= entity.velocity; }
                    case Direction.RIGHT -> { entity.solidArea.x += entity.velocity; }
                }
                // check if entity area intersects with object area
                if (entity.solidArea.intersects(object.solidArea)) {
                    if (object.isSolid) {
                        entity.collisionDetected = true;
                    }
                    if (!object.isSolid && player) {
                        objectIndex = idx;
                    }
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

    public int detectEntityCollisionWithEntities(Entity entity) {
        int entityIndex = Integer.MAX_VALUE;
        for (int idx = 0; idx < this.gamePanel.entities.size(); idx++) {
            Entity idxEntity = this.gamePanel.entities.get(idx);
            if (idxEntity != null && entity != idxEntity && entity.entityType != EntityType.OBJECT) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                idxEntity.solidArea.x = idxEntity.worldX + idxEntity.solidArea.x;
                idxEntity.solidArea.y = idxEntity.worldY + idxEntity.solidArea.y;
                switch (entity.direction) {
                    case Direction.UP -> { entity.solidArea.y -= entity.velocity; }
                    case Direction.DOWN -> { entity.solidArea.y += entity.velocity; }
                    case Direction.LEFT -> { entity.solidArea.x -= entity.velocity; }
                    case Direction.RIGHT -> { entity.solidArea.x += entity.velocity; }
                }
                // check if entity area intersects with idxEntity area
                if (entity.solidArea.intersects(idxEntity.solidArea)) {
                    entityIndex = idx;
                    if (idxEntity.isSolid) {
                        entity.collisionDetected = true;
                    }
                }
                // reset areas
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                idxEntity.solidArea.x = idxEntity.solidAreaDefaultX;
                idxEntity.solidArea.y = idxEntity.solidAreaDefaultY;
            }
        }
        return entityIndex;
    }

    public boolean detectEntityCollisionWithPlayer(Entity entity) {
        boolean entityCollidedWithPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        this.gamePanel.player.solidArea.x = this.gamePanel.player.worldX + this.gamePanel.player.solidArea.x;
        this.gamePanel.player.solidArea.y = this.gamePanel.player.worldY + this.gamePanel.player.solidArea.y;
        switch (entity.direction) {
            case Direction.UP -> { entity.solidArea.y -= entity.velocity; }
            case Direction.DOWN -> { entity.solidArea.y += entity.velocity; }
            case Direction.LEFT -> { entity.solidArea.x -= entity.velocity; }
            case Direction.RIGHT -> { entity.solidArea.x += entity.velocity; }
        }
        // check if entity area intersects with player area
        if (entity.solidArea.intersects(this.gamePanel.player.solidArea)) {
            entity.collisionDetected = true;
            entityCollidedWithPlayer = true;
        }
        // reset areas
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        this.gamePanel.player.solidArea.x = this.gamePanel.player.solidAreaDefaultX;
        this.gamePanel.player.solidArea.y = this.gamePanel.player.solidAreaDefaultY;

        return entityCollidedWithPlayer;
    }
}
