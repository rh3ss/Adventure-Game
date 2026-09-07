package entity;

import main.GamePanel;

import java.awt.*;

public class Particle extends Entity {
    private final Entity producerOfParticles;
    private final Color color;
    private int pxSize, deltaX, deltaY;

    public Particle(GamePanel p, Entity producer, Color color, int pxSize, int velocity, int maxHearts, int deltaX, int deltaY) {
        super(p);

        this.producerOfParticles = producer;
        this.color = color;
        this.pxSize = pxSize;
        this.velocity = velocity;
        this.maxHearts = maxHearts;
        this.currentHearts = this.maxHearts;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        int centerOffset = (this.gamePanel.tileSize / 2)- (this.pxSize / 2);
        this.worldX = this.producerOfParticles.worldX + centerOffset;
        this.worldY = this.producerOfParticles.worldY + centerOffset;
    }

    public void update() {
        this.currentHearts--;
        // implement gravity for particles
        if (this.currentHearts < (this.maxHearts / 3)) {
            this.deltaY++;
        }

        this.worldX += this.deltaX * this.velocity;
        this.worldY += this.deltaY * this.velocity;
        if (this.currentHearts < 0) {
            this.isAlive = false;
        }
    }

    public void draw(Graphics2D g2) {
        int screenX = this.worldX - this.gamePanel.player.worldX + this.gamePanel.player.screenX;
        int screenY = this.worldY - this.gamePanel.player.worldY + this.gamePanel.player.screenY;

        g2.setColor(this.color);
        g2.fillRect(screenX, screenY, this.pxSize, this.pxSize);
    }
}
