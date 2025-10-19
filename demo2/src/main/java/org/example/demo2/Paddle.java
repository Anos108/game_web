package org.example.demo2;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Paddle {
  private double x, y;
  static Image img;
  private double SPEED = 3.5;

  Paddle(double x, double y) {
    this.x = x;
    this.y = y;
    img =
        new Image(
            Objects.requireNonNull(
                getClass().getResourceAsStream(Config.IMAGE_PATH + "paddle.png")));
  }

  void render(GraphicsContext gc) {
    gc.drawImage(img, x, y, 120, 120);
  }

  void update(boolean left, boolean right) {
    if (left) {
      this.x -= this.SPEED;
    }
    if (right) {
      this.x += this.SPEED;
    }

  }

  public void setPaddle(double x, double y) {
    this.x = x;
    this.y = y;
  }

    public Rectangle2D getBounds(){
        return new Rectangle2D(x, y, 90, 0);
    }
}
