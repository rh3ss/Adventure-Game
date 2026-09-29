package environment;

import main.GamePanel;

import java.awt.Graphics2D;

public class EnvironmentManager {
    private final GamePanel gamePanel;

    public EnvironmentManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setup() {
        this.gamePanel.maps.get(1).lighting = new Lighting(this.gamePanel);
    }

    public void update() {
        if (this.gamePanel.maps.get(this.gamePanel.currentMapNumber).lighting != null) {
            this.gamePanel.maps.get(this.gamePanel.currentMapNumber).lighting.update();
        }
    }

    public void draw(Graphics2D graphics2D) {
        if (this.gamePanel.maps.get(this.gamePanel.currentMapNumber).lighting != null) {
            this.gamePanel.maps.get(this.gamePanel.currentMapNumber).lighting.draw(graphics2D);
        }
    }
}
