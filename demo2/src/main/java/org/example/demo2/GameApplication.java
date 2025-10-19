package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class GameApplication extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    private GraphicsContext gc;
    private Ball ball;
    private Paddle paddle;
    private List<Bricks.Brick> bricks;
    static Image background;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setTitle("Arkanoid");
        stage.setScene(scene);
        stage.show();

        // khoi tao
        ball = new Ball(Config.ballX, Config.ballY);
        paddle = new Paddle(Config.paddleX, Config.paddleY);
        background =
                new Image(
                        Objects.requireNonNull(getClass().getResourceAsStream("/asset/images/background.jpg")));

        // Khởi tạo danh sách gạch và tạo màn chơi
        bricks = new ArrayList<>();
        createLevel();

        // key sensor
        scene.setOnKeyPressed(
                e -> {
                    if (e.getCode() == KeyCode.A) Config.leftPressed = true;
                    if (e.getCode() == KeyCode.D) Config.rightPressed = true;
                });
        scene.setOnKeyReleased(
                e -> {
                    if (e.getCode() == KeyCode.A) Config.leftPressed = false;
                    if (e.getCode() == KeyCode.D) Config.rightPressed = false;
                });

        AnimationTimer gameLoop =
                new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        update();
                        render();
                    }
                };
        gameLoop.start();
    }

    private void createLevel() {
        Random random = new Random();
        int rows = 4;
        int cols = 8;
        double brickWidth = Config.brickWidth;
        double brickHeight = Config.brickHeight;
        double startX = 65; // Căn chỉnh vị trí bắt đầu của khối gạch (với kích thước 93px)
        double startY = 50; // Dịch lên một chút cho đủ chỗ
        double padding = 15; // Khoảng cách giữa các viên gạch (phù hợp với sprite sheet mới)

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                double x = startX + c * (brickWidth + padding);
                double y = startY + r * (brickHeight + padding);

                Bricks.Brick newBrick;
                int colorRandom = random.nextInt(8); // Có 8 loại gạch màu trong sprite sheet
                newBrick =
                        switch (colorRandom) {
                            case 0 -> new Bricks.BrickRed(x, y, brickWidth, brickHeight);
                            case 1 -> new Bricks.BrickOrange(x, y, brickWidth, brickHeight);
                            case 2 -> new Bricks.BrickYellow(x, y, brickWidth, brickHeight);
                            case 3 -> new Bricks.BrickGreen(x, y, brickWidth, brickHeight);
                            case 4 -> new Bricks.BrickLightBlue(x, y, brickWidth, brickHeight);
                            case 5 -> new Bricks.BrickBlue(x, y, brickWidth, brickHeight);
                            case 6 -> new Bricks.BrickPink(x, y, brickWidth, brickHeight);
                            default -> new Bricks.BrickPurple(x, y, brickWidth, brickHeight);
                        };
                bricks.add(newBrick);
            }
        }
    }

    private void update() {
        ball.update();
        paddle.update(Config.leftPressed, Config.rightPressed);
    }

    private void render() {
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
        ball.render(gc);
        paddle.render(gc);
        for (Bricks.Brick brick : bricks) {
            brick.render(gc);
        }
    }
}
