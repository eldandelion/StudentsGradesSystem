module com.example.studentsgradessystem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.studentsgradessystem to javafx.fxml;
    exports com.example.studentsgradessystem;
    exports com.example.studentsgradessystem.presentation;
    opens com.example.studentsgradessystem.presentation to javafx.fxml;
}