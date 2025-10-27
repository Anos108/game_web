package org.example.demo2;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Ball {
    private double x,y;
    static Image img;
    private double SPEED;
    private double speedUp;
    private double interactX=1;
    private double interactY=1;
    private double angleOffset = 0;
    Ball(double x, double y){
        this.x=x;
        this.y=y;
        img = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream(Config.IMAGE_PATH+"ball.png")

        ));
    }

    public void setSpeedUp(double speedUp) {
        this.speedUp = speedUp;
    }
    public void setSpeed(double speed){
        this.SPEED=speed;
    }

    void render(GraphicsContext gc){
        gc.drawImage(img, x, y, 50, 50);
    }
    public Rectangle2D getBounds(){
        return new Rectangle2D(x, y, 25, 25);
    }
    void update(){
        this.SPEED+=speedUp;
        this.x=this.x+(Config.gravityX+this.angleOffset)*this.SPEED*interactX;
        this.y=this.y+Config.gravityY*this.SPEED*interactY;

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


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngleOffset() {
        return angleOffset;
    }

    public void setAngleOffset(double angleOffset) {
        this.angleOffset = angleOffset;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }


    public double getSize() {
        return 50;
    }

    public void setBall(double x, double y) {
        this.x=x;
        this.y=y;
    }

}
