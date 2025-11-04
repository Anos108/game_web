package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class Config {
    public static GraphicsContext gc;
    public static AnimationTimer gameLoop;
    public static Image background;
    public static String difficulty;
    public static Stage primaryStage;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final double gravityX = 0.4;
    public static final double gravityY = 0.4;

    public static boolean leftPressed = false;
    public static boolean rightPressed = false;

    public static final double ballX = 50;
    public static final double ballY = 300;

    public static final double paddleX = 0;
    public static final double paddleY = 700;
    public static final double paddleWidth = 906/7;
    public static final double paddleHeight = 484/7;


    public static final double brickX = 150;
    public static final double brickY = 100;
    public static final double brickWidth = (double) 64;
    public static final double brickHeight = (double) 32;

    public static final String IMAGE_PATH = "/asset/images/";
    public static final String SOUND_PATH = "/asset/sounds/";
    public static final Font pixelFont =
            Font.loadFont(
                    Objects.requireNonNull(Config.class.getResourceAsStream("/asset/fonts/font.ttf")), 40);
    public static double Volume = 0.5;

  public static boolean interact(Rectangle2D a, Rectangle2D b) {
    return a.intersects(b);
  }

  public static void setScore(int score, String level) {
    switch (level) {
      case "easy":
        if (score > ScoreManager.readEasy()) {
          ScoreManager.writeEasy(score);
        }
        break;
      case "medium":
        if (score > ScoreManager.readMedium()) {
          ScoreManager.writeMedium(score);
        }
        break;
      case "hard":
        if (score > ScoreManager.readHard()) {
          ScoreManager.writeHard(score);
        }
        break;
    }
  }
    public static int getScore(String level) {
        return switch (level) {
            case "easy" -> ScoreManager.readEasy();
            case "medium" -> ScoreManager.readMedium();
            case "hard" -> ScoreManager.readHard();
            default -> 0;
        };
    }
}
