package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Paddle {
    private double x, y;
    static Image img;
    private double SPEED=1.5;


    Paddle(double x, double y) {
        this.x = x;
        this.y = y;
        img =
                new Image(
                        Objects.requireNonNull(getClass().getResourceAsStream("/asset/images/paddle.png")));
    }

    void render(GraphicsContext gc) {
        gc.drawImage(img, x, y, 100, 20);
    }

    void update(boolean left, boolean right){
        if (left){
            this.x-=this.SPEED;
        }
        if (right){
            this.x+=this.SPEED;
        }
    }

    public void setPaddle(double x, double y) {
        this.x=x;
        this.y=y;
    }
}
