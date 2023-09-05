package com.example.studentsgradessystem.data.database;

import com.example.studentsgradessystem.data.SubjectData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDB {

    private final String projectDir = System.getProperty("user.dir");
    private Connection connection = null;

    private Statement statement = null;

    public SubjectDB() {
        try {
            // Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl = "jdbc:sqlite:" + projectDir + "/subject.db";
            connection = DriverManager.getConnection(jdbcUrl);
            statement = connection.createStatement();

            // Create a table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS subject (subjectId BIGINT PRIMARY KEY, subjectName TEXT)";
            statement.execute(createTableQuery);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public SubjectData getSubjectById(long subjectId) {
        SubjectData subjectData = null;

        try {
            String query = "SELECT * FROM subject WHERE subjectId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, subjectId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String subjectName = resultSet.getString("subjectName");
                subjectData = new SubjectData(subjectId, subjectName);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectData;
    }
    public List<SubjectData> getSubjects() {
        List<SubjectData> subjects = new ArrayList<>();

        try {
            String query = "SELECT * FROM subject";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long subjectId = resultSet.getInt("subjectId");
                String subjectName = resultSet.getString("subjectName");

                SubjectData subject = new SubjectData(subjectId, subjectName);
                subjects.add(subject);
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }


    public boolean insertSubject(SubjectData subjectData) {
        try {
            String query = "INSERT INTO subject (subjectId, subjectName) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, subjectData.getSubjectId());
            preparedStatement.setString(2, subjectData.getSubjectName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteSubject(int subjectId) {
        try {
            String query = "DELETE FROM subject WHERE subjectId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, subjectId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
