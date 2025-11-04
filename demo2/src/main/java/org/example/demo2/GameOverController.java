package org.example.demo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.scene.control.Label;

public class GameOverController {
    @FXML
    private Label scoreLabel;
    public void setScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    @FXML
    void handleExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void handleBackToMenu(ActionEvent event) {
        try {
            GameplayManager.resetState();
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo2/MainMenu.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, Config.WIDTH, Config.HEIGHT));
            GameView.gameOver=false;
            GameplayManager.resetLife();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handlePlayAgain(ActionEvent event){
        try {
            GameplayManager.resetState();
            Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo2/Difficulty.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, Config.WIDTH, Config.HEIGHT));
            GameView.gameOver=false;
            GameplayManager.resetLife();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
