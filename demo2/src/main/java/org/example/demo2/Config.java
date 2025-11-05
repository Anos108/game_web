package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class Config {
    // Khởi tạo Singleton
    private static Config instance;
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    // Hằng số thực sự không đổi
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static final double GRAVITY_X = 0.4;
    public static final double GRAVITY_Y = 0.4;
    public static final double BALL_START_X = 50;
    public static final double BALL_START_Y = 300;
    public static final double PADDLE_START_X = 0;
    public static final double PADDLE_START_Y = 700;
    public static final double PADDLE_WIDTH = 906.0 / 7;
    public static final double PADDLE_HEIGHT = 484.0 / 7;
    public static final double BRICK_X = 150;
    public static final double BRICK_Y = 100;
    public static final double BRICK_WIDTH = 64;
    public static final double BRICK_HEIGHT = 32;
    public static final String IMAGE_PATH = "/asset/images/";
    public static final String SOUND_PATH = "/asset/sounds/";
    public static final Font PIXEL_FONT =
            Font.loadFont(
                    Objects.requireNonNull(Config.class.getResourceAsStream("/asset/fonts/font.ttf")), 40);

    // Biến cấu hình & trạng thái
    private GraphicsContext gc;
    private AnimationTimer gameLoop;
    private Image background;
    private String difficulty;
    private Stage primaryStage;
    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private double speed;
    private double speedUp;
    private double volume = 0.5;

    // Getter/setter cho các thuộc tính cấu hình
    public GraphicsContext getGc() {
        return gc;
    }
    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    public AnimationTimer getGameLoop() {
        return gameLoop;
    }
    public void setGameLoop(AnimationTimer gameLoop) {
        this.gameLoop = gameLoop;
    }

    public Image getBackground() {
        return background;
    }
    public void setBackground(Image background) {
        this.background = background;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }
    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }
    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeedUp() {
        return speedUp;
    }
    public void setSpeedUp(double speedUp) {
        this.speedUp = speedUp;
    }

    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }

    // Hàm tiện ích (giữ lại nhưng đổi static thành non-static nếu cần dùng instance)
    public boolean interact(Rectangle2D a, Rectangle2D b) {
        return a.intersects(b);
    }

    public void setScore(int score, String level) {
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

    public int getScore(String level) {
        return switch (level) {
            case "easy" -> ScoreManager.readEasy();
            case "medium" -> ScoreManager.readMedium();
            case "hard" -> ScoreManager.readHard();
            default -> 0;
        };
    }
}