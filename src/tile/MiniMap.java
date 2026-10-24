package tile;

import entity.Entity;
import main.GamePanel;
import main.MapData;
import tileFix.TileFix;
import tileInteractive.InteractiveTile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class MiniMap extends TileManager {
    private final GamePanel gamePanel;
    public Map<Integer, BufferedImage> worldMaps;
    public boolean showMiniMap;

    public MiniMap(GamePanel gamePanel) {
        super(gamePanel);
        this.gamePanel = gamePanel;
        this.worldMaps = new HashMap<>();

        // default mini map should be visible
        this.showMiniMap = true;
    }

    public void createMiniMapImages() {
        int worldMapIndex = 0;
        for (MapData mapData : this.gamePanel.maps.values()) {
            int mapDataWidth = this.gamePanel.tileSize * mapData.maxWorldColumns;
            int mapDataHeight = this.gamePanel.tileSize * mapData.maxWorldRows;
            // create image
            this.worldMaps.put(worldMapIndex, new BufferedImage(mapDataWidth, mapDataHeight, BufferedImage.TYPE_INT_ARGB));
            Graphics2D g2 = (Graphics2D) this.worldMaps.get(worldMapIndex).createGraphics();
            // draw tiles to image
            for (int column = 0; column < mapData.maxWorldColumns; ++column) {
                for (int row = 0; row < mapData.maxWorldRows; ++row) {
                    int tileNumber = this.mapTileNumbers[worldMapIndex][column][row];
                    g2.drawImage(this.tiles.get(tileNumber).image, this.gamePanel.tileSize * column, this.gamePanel.tileSize * row, null);
                }
            }
            // draw fix tiles and interactive tiles to image
            for (Entity entity : mapData.entities) {
                if (entity instanceof TileFix || entity instanceof InteractiveTile) {
                    g2.drawImage(entity.down1, entity.worldX, entity.worldY, null);
                }
            }
            worldMapIndex++;
        }
    }
}
