package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Ball {
    private int x,y;
    static Image img;
    Ball(int x, int y){
        this.x=x;
        this.y=y;
        img = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/asset/images/ball.png")

        ));
    }

    void render(GraphicsContext gc){
        gc.drawImage(img, x, y, 50, 50);
    }
}
