package com.example.studentsgradessystem.data.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TeacherDB {

    private final String projectDir = System.getProperty("user.dir");
    private final Connection connection;
    private final Statement statement;

    public TeacherDB() {
        try {
            // Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl = "jdbc:sqlite:" + projectDir + "/teacher.db";
            connection = DriverManager.getConnection(jdbcUrl);
            statement = connection.createStatement();

            // Create tables if they don't exist
            String createTeacherTableQuery = "CREATE TABLE IF NOT EXISTS teacher (teacherId BIGINT, email TEXT, name TEXT, password TEXT)";
            statement.execute(createTeacherTableQuery);

            // Create additional tables if necessary (e.g., subjects, students)

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
