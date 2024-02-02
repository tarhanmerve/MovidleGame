module com.example.movidlegame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.movidlegame to javafx.fxml;
    exports com.example.movidlegame;
}