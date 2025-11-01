package org.example.demo2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static java.lang.System.in;

public class PowerUp {
    private final String powerUpUrl;
    private final double x;
    private double y;
    private boolean dead = false;
    private final Image image ;

    PowerUp( double x, double y) {
        int powerPick = (int)(Math.random() * 3) + 1;
        if (powerPick == 1) {
            powerUpUrl = "ballBoost.png";
        }
        else if (powerPick == 2) {
            powerUpUrl = "lifeBoost.png";
        }
        else powerUpUrl = "slowBoost.png";
        this.x = x;
        this.y = y;
        image = new javafx.scene.image.Image(
                Objects.requireNonNull(
                        getClass().getResourceAsStream(Config.IMAGE_PATH + powerUpUrl)));
    }

    public void setDead() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public int update(Rectangle2D ball) {
        y+=1;
        if(Config.interact(getBounds(),ball)){
            switch (powerUpUrl) {
                case "lifeBoost.png" :
                    return 3;
                case "ballBoost.png":
                    return 1; // ball Boost
                case "slowBoost.png":
                    return 2;
            }
        }
        return 0;
    }
    public Rectangle2D getBounds(){
        return new Rectangle2D(x, y, 50, 50);
    }
    public void renderPowerUp(GraphicsContext gc) {

        gc.drawImage(image,x,y,50,50);
    }

}
