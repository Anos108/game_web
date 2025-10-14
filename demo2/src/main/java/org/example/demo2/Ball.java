package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Ball {
    private double x,y;
    static Image img;
    private double SPEED=1;
    Ball(double x, double y){
        this.x=x;
        this.y=y;
        img = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/asset/images/ball.png")

        ));
    }

    void render(GraphicsContext gc){
        gc.drawImage(img, x, y, 50, 50);
    }
    void update(){
        this.y=this.y+Config.GRAVITY*this.SPEED;
        SPEED+=0.003;
    }

    public void setBall(double x, double y) {
        this.x=x;
        this.y=y;
    }
}
