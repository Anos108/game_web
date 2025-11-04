package org.example.demo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class GameOverController {
    @FXML
    private Label scoreLabel;
    
    @FXML
    public void initialize() {
        // Load font từ resources
        Font pixelFont = Font.loadFont(
            getClass().getResourceAsStream("/asset/fonts/font.ttf"), 
            40
        );
        
        if (pixelFont != null) {
            scoreLabel.setFont(pixelFont);
            scoreLabel.setTextFill(Color.ORANGE);
        } else {
            System.out.println("Font không load được!");
        }
    }
    
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
            GameView.gameplayManager.setGameOver(false);
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
            GameView.gameplayManager.setGameOver(false);
            GameplayManager.resetLife();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
