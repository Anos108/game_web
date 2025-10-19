package org.example.demo2;

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

    // Giới hạn paddle trong màn hình
    if (this.x < 0) {
      this.x = 0;
    }
    if (this.x > Config.WIDTH - 120) { // 120 là chiều rộng paddle
      this.x = Config.WIDTH - 120;
    }
  }

  public void setPaddle(double x, double y) {
    this.x = x;
    this.y = y;
  }

  // Getters
  public double getX() { return x; }
  public double getY() { return y; }
  public double getWidth() { return 120; } // Chiều rộng paddle
}
