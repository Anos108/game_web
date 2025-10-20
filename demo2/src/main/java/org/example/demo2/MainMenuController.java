package org.example.demo2; // Phải đúng package của ông

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

  @FXML private Button startButton;

  @FXML private Button exitButton;

  @FXML
  void initialize() {
    System.out.println("Menu Controller đã được khởi tạo!");
  }

  @FXML
  void handleStartGame(ActionEvent event) {
    System.out.println("Nút Start đã được bấm! Đang khởi tạo GameView...");

    try {

      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

      GameView gameView = new GameView();

      gameView.startGame(stage);

    } catch (Exception e) {
      System.err.println("LỖI: Không thể khởi động GameView.");
      e.printStackTrace();
    }
  }

  @FXML
  void handleExit(ActionEvent event) {
    System.out.println("Nút Exit đã được bấm! Tạm biệt.");
    // Đóng ứng dụng
    Platform.exit();
  }
}
