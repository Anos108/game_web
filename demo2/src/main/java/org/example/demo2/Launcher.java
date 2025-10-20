package org.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException; // Nhớ import

public class Launcher extends Application { // Tên class của ông có thể khác

    // Vứt hết biến WIDTH, HEIGHT, gc... ở đây đi

    @Override
    public void start(Stage primaryStage) { // Tên biến 'stage' hay 'primaryStage' đều được
        try {
            // 1. TÌM FILE FXML (Sửa lại đường dẫn nếu cần)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo2/MainMenu.fxml"));

            // 2. LOAD NÓ
            Parent root = loader.load(); // Đây là lúc MenuController được tạo

            // 3. TẠO SCENE TỪ CÁI ROOT (GIAO DIỆN) VỪA LOAD
            Scene scene = new Scene(root);


            // 4. HIỂN THỊ
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