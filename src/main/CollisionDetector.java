package main;

import entity.Entity;
import enums.Direction;

public class CollisionDetector {
    private final GamePanel gamePanel;

    public CollisionDetector(GamePanel p) {
        this.gamePanel = p;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftColumn = entityLeftWorldX / this.gamePanel.tileSize;
        int entityRightColumn = entityLeftWorldX / this.gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / this.gamePanel.tileSize;
        int entityBottowRow = entityBottomWorldY / this.gamePanel.tileSize;

        int tileNumber1, tileNumber2;
        switch (entity.direction) {
            case Direction.UP:
                entityTopRow = (entityTopWorldY - entity.velocity) / this.gamePanel.tileSize;
                tileNumber1 = this.gamePanel.tileManager.mapTileNumbers[entityLeftColumn][entityTopRow];
                tileNumber2 = this.gamePanel.tileManager.mapTileNumbers[entityRightColumn][entityTopRow];
                if(this.gamePanel.tileManager.tile[tileNumber1].collision) {
                    entity.collisionOn = true;
                }
                break;
            case Direction.DOWN:
                break;
            case Direction.LEFT:
                break;
            case Direction.RIGHT:
                break;
        }
    }
}
