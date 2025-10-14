package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Paddle {
  private int x, y;
  static Image img;

  Paddle(int x, int y) {
    this.x = x;
    this.y = y;
    img =
        new Image(
            Objects.requireNonNull(getClass().getResourceAsStream("/asset/images/paddle.png")));
  }

  void render(GraphicsContext gc) {
    gc.drawImage(img, x, y, 100, 20);
  }
}
