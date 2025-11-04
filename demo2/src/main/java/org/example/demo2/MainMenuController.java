package org.example.demo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class MainMenuController {


  public static MediaPlayer mediaPlayer;

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }


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
              mediaPlayer.setVolume(Config.Volume);
              mediaPlayer.play();
              System.out.println("Nhạc nền menu đang phát...");
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
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo2/Setting.fxml"));
        Parent root = loader.load();
        SettingController controller = loader.getController();
        controller.setMediaPlayer(mediaPlayer);

        Stage dialog = new Stage();
        dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.setTitle("Settings");
        dialog.setScene(new Scene(root));
        dialog.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
