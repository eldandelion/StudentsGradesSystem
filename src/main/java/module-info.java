module com.example.studentsgradessystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens com.example.studentsgradessystem to javafx.fxml;
    exports com.example.studentsgradessystem.data;
    exports com.example.studentsgradessystem.domain.pojo;
    exports com.example.studentsgradessystem.domain.events;
    exports com.example.studentsgradessystem.domain.repositories;
    exports com.example.studentsgradessystem.presentation;
    opens com.example.studentsgradessystem.presentation to javafx.fxml;
}