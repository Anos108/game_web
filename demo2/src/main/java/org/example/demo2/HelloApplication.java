package org.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final int HEIGHT = 800;
    private static final int WEIGHT = 800;
    private GraphicsContext gc;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root,WEIGHT,HEIGHT);

        stage.setTitle("Arkanoid");
        stage.setScene(scene);
        stage.show();

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

    }
}
