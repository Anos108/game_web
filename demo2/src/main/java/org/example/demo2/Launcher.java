package org.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException; // Nhớ import

public class Launcher extends Application {

    // Vứt hết biến WIDTH, HEIGHT, gc... ở đây đi

    @Override
    public void start(Stage primaryStage) {
        try {
            // find fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo2/MainMenu.fxml"));

            // load fxml file
            Parent root = loader.load(); // Đây là lúc MenuController được tạo

            // create a scene
            Scene scene = new Scene(root);


            // display the scene
            primaryStage.setTitle("Arkanoid"); // Sửa title cho đúng
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("LỖI TO: Không load được menu.fxml. Kiểm tra lại đường dẫn.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}