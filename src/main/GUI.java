package main;

import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class GUI {
    private final GamePanel gamePanel;
    private final Font font;
    private final BufferedImage keyImage;
    private int countShownMessageFrames = 0;
    public boolean messageOn = false;
    public String message = "";
    public boolean gameFinished = false;

    private double playedTime = 0;
    private final DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    public GUI(GamePanel p) {
        this.gamePanel = p;
        this.font = new Font("Arial", Font.PLAIN, 40);
        ObjectKey key = new ObjectKey(this.gamePanel);
        this.keyImage = key.image;
    }

    public void showMessage(String text) {
        this.message = text;
        this.messageOn = true;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(this.font);
        g2.setColor(Color.WHITE);
        if(this.gameFinished) {
            String finishText = "You found the End!";
            int finishTextLength = (int) g2.getFontMetrics().getStringBounds(finishText, g2).getWidth();
            int x = (this.gamePanel.screenWidth / 2) - finishTextLength / 2;
            int y = this.gamePanel.screenHeight / 2;
            g2.drawString(finishText, x, y);
            this.gamePanel.gameThread = null;
            return;
        }

        g2.drawImage(this.keyImage, this.gamePanel.tileSize / 2, this.gamePanel.tileSize / 2, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
        g2.drawString("x " + this.gamePanel.player.countPickedUpKeys, 74, 65);

        this.playedTime += (double) 1/60;
        g2.drawString("Time: " + this.decimalFormat.format(this.playedTime), this.gamePanel.tileSize*11, 65);

        if(this.messageOn) {
            g2.setFont(g2.getFont().deriveFont(25F));
            g2.drawString(this.message, this.gamePanel.tileSize / 2, this.gamePanel.screenHeight / 2);

            this.countShownMessageFrames++;
            if(this.countShownMessageFrames > 120) {
                this.countShownMessageFrames = 0;
                this.messageOn = false;
            }
        }
    }
}
