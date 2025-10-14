package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Brick {
    private double x, y;
    private double width, height;
    static Image img;

    Brick(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        img =
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/asset/images/brick.png")));
    }
    void render(GraphicsContext gc){
        gc.drawImage(img, x, y, width, height);
    }
}
