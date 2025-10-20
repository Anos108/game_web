package org.example.demo2;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Objects;

public class Ball {
  private double x, y;
  private double velocityX = 3; // Vận tốc theo trục X
  private double velocityY = 3; // Vận tốc theo trục Y
  private static final double BALL_SIZE = 20; // Kích thước bóng
  static Image img;

  Ball(double x, double y) {
    this.x = x;
    this.y = y;
    img =
        new Image(
            Objects.requireNonNull(getClass().getResourceAsStream(Config.IMAGE_PATH + "ball.png")));
  }

  void render(GraphicsContext gc) {
    gc.drawImage(img, x, y, BALL_SIZE, BALL_SIZE);
  }

  void update() {
    this.x += velocityX;
    this.y += velocityY;
  }

  Rectangle2D getBounds() {
    return new Rectangle2D(x, y, BALL_SIZE, BALL_SIZE);
  }

  void CheckWallCollision() {
    if (x <= 0 || x >= Config.WIDTH - BALL_SIZE) {
      velocityX = -velocityX;
      //x = 0;
    }
    if (x >= Config.WIDTH - BALL_SIZE) x = Config.WIDTH - BALL_SIZE;
    if (y <= 0) {
      velocityY = -velocityY;
      y = 0;
    }
    // Collision bottom
    if (y >= Config.HEIGHT - BALL_SIZE) {
      resetBall();
    }
  }

  private void resetBall() {
    this.x = Config.ballX;
    this.y = Config.ballY;
    this.velocityX = 3;
    this.velocityY = 3;
  }

  void checkPaddleCollision(Paddle paddle) {
    Rectangle2D paddleBounds = paddle.getBounds();
    Rectangle2D ballBounds = getBounds();
    if (ballBounds.intersects(paddleBounds)) {
      this.velocityY = -this.velocityY;
      double hitPos = this.x + BALL_SIZE / 2 - paddle.getX() - paddle.getWidth() / 2;
      this.velocityX = hitPos / 10;

    }
  }
}
