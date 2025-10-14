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

import java.util.Objects;

public class GameApplication extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    private GraphicsContext gc;
    private Ball ball;
    private Paddle paddle;
    private Brick brick;
    static Image background;


    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root,WIDTH,HEIGHT);

        stage.setTitle("Arkanoid");
        stage.setScene(scene);
        stage.show();

        //khoi tao
        ball = new Ball(Config.ballX,Config.ballY);
        paddle = new Paddle(Config.paddleX,Config.paddleY);
        brick = new Brick(Config.brickX, Config.brickY, Config.brickWidth, Config.brickHeight);
        background = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/asset/images/background.jpg")));

        //key sensor
        scene.setOnKeyPressed(e->{
            if (e.getCode() == KeyCode.A)  Config.leftPressed = true;
            if (e.getCode() == KeyCode.D) Config.rightPressed = true;
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.A)  Config.leftPressed = false;
            if (e.getCode() == KeyCode.D) Config.rightPressed = false;
        });

        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                    update();
                    render();
            }
        };
        gameLoop.start();

    }

    private void update(){
        ball.update();
        paddle.update(Config.leftPressed,Config.rightPressed);
    }
    private void render(){
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
        ball.render(gc);
        paddle.render(gc);
        brick.render(gc);

    }
}
