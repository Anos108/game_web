package org.example.demo2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameplayManager {
  private static int life = 3;
  private int ball_add = 0;
  public boolean gameOver = false;

  public void setGameOver(boolean gameOver) {
    this.gameOver = gameOver;
  }

  public boolean getGameOver() {
    return gameOver;
  }

  public int getBall_add() {
    return ball_add;
  }

  public void setBall_add(int ball_add) {
    this.ball_add = ball_add;
  }

  public void setBall_plus() {
    this.ball_add++;
  }

  private static final Image LIFE_IMG =
      new Image(
          Objects.requireNonNull(
              GameplayManager.class.getResourceAsStream(Config.IMAGE_PATH + "mang.png")));
  private static int score = 0;
  private static boolean checkLife = false;

  public static void setCheckLife(boolean checkLife) {
    GameplayManager.checkLife = checkLife;
  }

  public static boolean isCheckLife() {
    return checkLife;
  }

  public static void equalLife() {
    if (checkLife) {
      life--;
      checkLife = false;
    }
  }

  public static void setCheckLife() {
    checkLife = !checkLife;
  }

  public static int getLife() {
    return life;
  }

  public static void resetLife() {
    life = 3;
  }

  public static int plusLife() {
    return life++;
  }

  public void plusScore(int score) {
    GameplayManager.score += score;
  }

  public void resetScore() {
    GameplayManager.score = 0;
  }

  public static void renderLives(GraphicsContext gc) {
    double x = 0;
    double y = 0;
    double size = 64;
    double gap = 8;
    for (int i = 0; i < life; i++) {
      gc.drawImage(LIFE_IMG, x + i * (size + gap), y, size, size);
    }
  }

  public static void resetState() {
    life = 0;
    score = 0;
    checkLife = false;
  }

  public void createLevel(List<Bricks.Brick> bricks) {

    Random random = new Random();
    int rows = 4;
    int cols = 8;
    double brickWidth = Config.brickWidth;
    double brickHeight = Config.brickHeight;
    double startX = 65;
    double startY = 50;
    double padding = 15;

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < cols; c++) {
        double x = startX + c * (brickWidth + padding);
        double y = startY + r * (brickHeight + padding);

        Bricks.Brick newBrick;
        int colorRandom = random.nextInt(4);
        newBrick =
            switch (colorRandom) {
              case 0 -> new Bricks.BrickRed(x, y, brickWidth, brickHeight);
              case 1 -> new Bricks.BrickOrange(x, y, brickWidth, brickHeight);
              case 2 -> new Bricks.BrickGreen(x, y, brickWidth, brickHeight);
              default -> new Bricks.BrickPurple(x, y, brickWidth, brickHeight);
            };
        bricks.add(newBrick);
      }
    }
  }
  public void cleanLevel(List<Bricks.Brick> bricks, List<PowerUp> powerUps, List<Ball> balls) {
      balls.removeIf(Ball::isDead);
      bricks.removeIf(Bricks.Brick::isDestroyed);
      powerUps.removeIf(PowerUp::isDead);
  }

  public void  setLevel(Ball ball) {
      if (Config.difficulty.equals("easy")) {
          ball.setSpeed(3.5);
          ball.setSpeedUp(0.0001);
          Config.SPEED=3.5;
          Config.SPEEDUP=0.001;

      } else if (Config.difficulty.equals("medium")) {
          ball.setSpeed(5);
          ball.setSpeedUp(0.0005);
          Config.SPEED=5;
          Config.SPEEDUP=0.0005;

      } else if (Config.difficulty.equals("hard")) {
          ball.setSpeed(6);
          ball.setSpeedUp(0.001);
          Config.SPEED=6;
          Config.SPEEDUP=0.001;
      }
  }

  public static int getScore() {
    return score;
  }
}
