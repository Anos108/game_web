package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class GameView extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    private GraphicsContext gc;
    private Ball ball;
    private Paddle paddle;
    private List<Bricks.Brick> bricks;
    static Image background;
    private String difficulty;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Arkanoid");

        // Load MainMenu FXML
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo2/MainMenu.fxml"));
        Scene menuScene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(menuScene);
        stage.show();
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void startGame(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, WIDTH, HEIGHT);

        stage.setScene(scene);

        ball = new Ball(Config.ballX, Config.ballY);
        paddle = new Paddle(Config.paddleX, Config.paddleY);
        background =
                new Image(
                        Objects.requireNonNull(getClass().getResourceAsStream("/asset/images/background.png")));

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
        if(difficulty.equals("easy")) {
            ball.setSpeed(3.5);
            ball.setSpeedUp(0.0001);

        }
        else if(difficulty.equals("medium")) {
            ball.setSpeed(5);
            ball.setSpeedUp(0.0005);

        }
        else if(difficulty.equals("hard")) {
            ball.setSpeed(6);
            ball.setSpeedUp(0.001);

        }
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

    private void update() {
        ball.update();
        paddle.update(Config.leftPressed, Config.rightPressed, paddle.getBounds());
        if (Config.interact(ball.getBounds(), paddle.getBounds())) {
            Physic.ballPaddle(ball, paddle);
        }
        if (Wall.check_wall(ball.getBounds()) == 1) {
            ball.setInteractY();
        }
        if (Wall.check_wall(ball.getBounds()) == 2) {
            ball.setInteractX();
        }
        if (Wall.check_wall(ball.getBounds()) == 3) {
            ball.setInteractX();
        }
        if (Wall.check_wall(ball.getBounds()) == 4){
            Platform.exit();
        }

        for (Bricks.Brick brick : bricks) {
            brick.Update();
            if (Config.interact(ball.getBounds(), brick.getBounds())) {
                Physic.ballBrickCollision(ball, brick);
                brick.healthDown(); //giam health gach
                brick.setDestroyed(true);
                break;
            }
        }

        // 2) Purge sau vòng lặp
        bricks.removeIf(Bricks.Brick::isDestroyed);
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
