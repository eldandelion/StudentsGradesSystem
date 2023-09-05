module com.example.studentsgradessystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.studentsgradessystem to javafx.fxml;
    exports com.example.studentsgradessystem.data;
    exports com.example.studentsgradessystem.domain.pojo;
    exports com.example.studentsgradessystem.domain.react;
    exports com.example.studentsgradessystem.domain.repositories;
    exports com.example.studentsgradessystem.presentation;
    opens com.example.studentsgradessystem.presentation to javafx.fxml;
}