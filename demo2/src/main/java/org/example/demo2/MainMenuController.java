package org.example.demo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

  @FXML private Button startButton;
  @FXML private Button exitButton;
  @FXML private Button settingsButton;

  @FXML
  void initialize() {
    System.out.println("Menu Controller đã được khởi tạo!");
  }

  @FXML
  void handleStartGame(ActionEvent event) {
    System.out.println("Nút Start đã được bấm! Đang khởi tạo GameView...");

    try {
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      Parent root = FXMLLoader.load(getClass().getResource("/org/example/demo2/Difficulty.fxml"));
      Scene difficultyScene = new Scene(root, 800, 800);
      stage.setScene(difficultyScene);

    } catch (Exception e) {
      System.err.println("LỖI: Không thể khởi động GameView.");
      e.printStackTrace();
    }
  }

  @FXML
  void handleExit(ActionEvent event) {
    System.out.println("Nút Exit đã được bấm! Tạm biệt.");
    Platform.exit();
  }

  @FXML
  void handleSettings(ActionEvent event) {
    System.out.println("Nút Settings đã được bấm! ");
  }
}
