package main;


import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class GUI {
    private final GamePanel gamePanel;
    private Graphics2D graphics2D;
    private final Font font;
    private int countShownMessageFrames = 0;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;

    public GUI(GamePanel p) {
        this.gamePanel = p;
        this.font = new Font("Arial", Font.PLAIN, 40);
    }

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.graphics2D = g2;

        switch (this.gamePanel.gameState) {
            case PLAYING -> {

            }
            case PAUSED -> {
                this.drawPausedGameScreen();
            }
        }
    }

    private void drawPausedGameScreen() {
        this.graphics2D.setFont(font);
        this.graphics2D.setColor(Color.WHITE);
        this.graphics2D.setFont(this.graphics2D.getFont().deriveFont(Font.PLAIN, 80F));

        String pausedText = "PAUSED";
        int xPos = this.calcXPositionForCenteredText(pausedText);
        int yPos = this.calcYPositionForCenteredText(pausedText);
        this.graphics2D.drawString(pausedText, xPos, yPos);
    }

    private int calcXPositionForCenteredText(String text) {
        int textLength = (int) this.graphics2D.getFontMetrics().getStringBounds(text, this.graphics2D).getWidth();
        return (this.gamePanel.screenWidth / 2) - (textLength / 2);
    }

    private int calcYPositionForCenteredText(String text) {
        int textHeight = (int) this.graphics2D.getFontMetrics().getStringBounds(text, this.graphics2D).getHeight();
        return (this.gamePanel.screenHeight / 2) - (textHeight / 2);
    }
}
