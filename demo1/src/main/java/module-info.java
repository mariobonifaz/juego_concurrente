module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example.demo1.Controller;
    opens com.example.demo1.Controller to javafx.fxml;
}