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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class MainMenuController {

  @FXML private Button startButton;
  @FXML private Button exitButton;
  @FXML private Button settingsButton;
  private MediaPlayer mediaPlayer;

  @FXML
  void initialize() {
    System.out.println("Menu Controller đã được khởi tạo!");
      try {
          // Tải file âm thanh menu
          URL musicPath = getClass().getResource(Config.SOUND_PATH + "msbg.mp3");

          if (musicPath != null) {
              Media sound = new Media(musicPath.toExternalForm());
              mediaPlayer = new MediaPlayer(sound);
              mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // lặp vô hạn
              mediaPlayer.setVolume(0.3); // âm lượng 30%
              mediaPlayer.play();
              System.out.println("Nhạc nền menu đang phát...");
          } else {
              System.err.println("Không tìm thấy file menu_music.mp3!");
          }
      } catch (Exception e) {
          e.printStackTrace();
      }
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
