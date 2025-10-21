package org.example.demo2;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Ball {
    private double x,y;
    static Image img;
    private double SPEED=5;
    private double interactX=1;
    private double interactY=1;
    Ball(double x, double y){
        this.x=x;
        this.y=y;
        img = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream(Config.IMAGE_PATH+"ball.png")

        ));
    }

    void render(GraphicsContext gc){
        gc.drawImage(img, x, y, 50, 50);
    }
    public Rectangle2D getBounds(){
        return new Rectangle2D(x, y, 25, 25);
    }
    void update(){
        this.x=this.x+Config.gravityX*this.SPEED*interactX;
        this.y=this.y+Config.gravityY*this.SPEED*interactY;
        SPEED+=0.003;
    }
    public void  setInteract(){
        this.interactX=-this.interactX;
        this.interactY=-this.interactY;
    }

    public void setInteractX() {
        this.interactX = -this.interactX;
    }

    public void setInteractY() {
        this.interactY = -this.interactY;
    }

    public void setBall(double x, double y) {
        this.x=x;
        this.y=y;
    }
}
