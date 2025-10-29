package org.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DifficultyController {
  @FXML private Button easyButton;
  @FXML private Button mediumButton;
  @FXML private Button hardButton;

  @FXML
  void initialize() {
    System.out.println("Đã khởi tạo Difficulty Controller");
  }

  @FXML
  void handleEasy(ActionEvent event) {
      if (MainMenuController.mediaPlayer != null) {
          MainMenuController.mediaPlayer.stop();
      }

    System.out.println("Easy Mode");
    startGameWithDifficulty(event, "easy");
  }

  @FXML
  void handleMedium(ActionEvent event) {
      if (MainMenuController.mediaPlayer != null) {
          MainMenuController.mediaPlayer.stop();
      }
    System.out.println("Medium Mode");
    startGameWithDifficulty(event, "medium");
  }

  @FXML
  void handleHard(ActionEvent event) {
      if (MainMenuController.mediaPlayer != null) {
          MainMenuController.mediaPlayer.stop();
      }
    System.out.println("Hard Mode");
    startGameWithDifficulty(event, "hard");
  }

  private void startGameWithDifficulty(ActionEvent event, String difficulty) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

    GameView gameView = new GameView();
    gameView.setDifficulty(difficulty);
    gameView.startGame(stage);
  }
}
