package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class GameApplication extends Application {
    private static final int HEIGHT = 800;
    private static final int WIDTH = 800;
    private GraphicsContext gc;
    private Ball ball;
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
        ball = new Ball(50,50);
        background = new Image(Objects.requireNonNull(
                getClass().getResourceAsStream("/asset/images/background.jpg")));

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

    }
    private void render(){
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        gc.drawImage(background, 0, 0, WIDTH, HEIGHT);
        ball.render(gc);
    }
}
