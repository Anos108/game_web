package org.example.demo2;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Paddle {
  private double x, y;
  static Image img;
  private double SPEED = 3.5;
  private boolean moving = false;

  Paddle(double x, double y) {
    this.x = x;
    this.y = y;
    img =
        new Image(
            Objects.requireNonNull(
                getClass().getResourceAsStream(Config.IMAGE_PATH + "paddle.png")));
  }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    void render(GraphicsContext gc) {
    gc.drawImage(img, x, y,Config.paddleWidth , Config.paddleHeight );
  }

  void update(boolean left, boolean right,Rectangle2D bounds) {
    if (left && ((Wall.check_wall(bounds))!=2)) {
      this.x -= this.SPEED;
      this.moving = true;
    }
    if (right && ((Wall.check_wall(bounds))!=3)) {
      this.x += this.SPEED;
      this.moving = true;
    }
    else  {
      this.moving = false;
    }


  }

    public boolean isMoving() {
        return moving;
    }

    public void setPaddle(double x, double y) {
    this.x = x;
    this.y = y;
  }

    public Rectangle2D getBounds(){
        return new Rectangle2D(x, y, Config.paddleWidth , Config.paddleHeight);
    }
}
