package org.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SettingController {

    @FXML
    private Slider volumeSlide;

    private MediaPlayer mediaPlayer;

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        if (volumeSlide != null) {
            volumeSlide.setValue(Config.getInstance().getVolume() * 100);
            if (this.mediaPlayer != null) {
                this.mediaPlayer.setVolume(Config.getInstance().getVolume());
            }
            volumeSlide.valueProperty().addListener((obs, oldVal, newVal) -> {
                double normalized = newVal.doubleValue() / 100.0;
                Config.getInstance().setVolume(normalized);
                if (this.mediaPlayer != null) {
                    this.mediaPlayer.setVolume(normalized);
                }
            });
        }
    }

    @FXML
    void handleClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}

