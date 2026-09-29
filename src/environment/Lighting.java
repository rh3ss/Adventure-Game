package environment;

import main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class Lighting {
    private final GamePanel gamePanel;
    private BufferedImage darknessFilterImage;
    private final int GRADATION_STEPS = 10;

    public Lighting(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.setLightSource();
    }

    public void update() {
        if (this.gamePanel.player.lightUpdated) {
            this.setLightSource();
            this.gamePanel.player.lightUpdated = false;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.drawImage(this.darknessFilterImage, 0, 0, null);
    }

    public void setLightSource() {
        this.darknessFilterImage = new BufferedImage(gamePanel.screenWidth, gamePanel.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = (Graphics2D) this.darknessFilterImage.getGraphics();

        if (this.gamePanel.player.currentLight == null) {
            graphics2D.setColor(new Color(0, 0, 0, 0.80F));
        }
        else {
            // get coords for light circle
            int circleCenterX = gamePanel.player.screenX + (gamePanel.tileSize / 2);
            int circleCenterY = gamePanel.player.screenY + (gamePanel.tileSize / 2);
            // calc and paint light circle
            RadialGradientPaint radialGradientPaint = new RadialGradientPaint(circleCenterX, circleCenterY, this.gamePanel.player.currentLight.lightRadius, this.getFractions(), this.getColors());
            graphics2D.setPaint(radialGradientPaint);
        }
        graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
        graphics2D.dispose();
    }

    private Color[] getColors() {
        return new Color[] {
            new Color(0, 0, 0, 0.10f),
            new Color(0, 0, 0, 0.10f),
            new Color(0, 0, 0, 0.10f),
            new Color(0, 0, 0, 0.10f),
            new Color(0, 0, 0, 0.42f),
            new Color(0, 0, 0, 0.52f),
            new Color(0, 0, 0, 0.61f),
            new Color(0, 0, 0, 0.69f),
            new Color(0, 0, 0, 0.76f),
            new Color(0, 0, 0, 0.80f)
        };
    }

    private float[] getFractions() {
        return new float[] {0f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.85f, 0.9f, 0.95f, 1f};
    }
}
