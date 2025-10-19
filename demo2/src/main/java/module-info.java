module org.example.demo2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens org.example.demo2 to javafx.fxml;
    exports org.example.demo2;
}