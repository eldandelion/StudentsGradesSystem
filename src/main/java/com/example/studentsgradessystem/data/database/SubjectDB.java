package com.example.studentsgradessystem.data.database;


import com.example.studentsgradessystem.data.pojo.SubjectData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDB {

    private final String projectDir = System.getProperty("user.dir");
    private final Connection connection;
    private final Statement statement;

    public SubjectDB() {
        try {
            // Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl = "jdbc:sqlite:" + projectDir + "/subject.db";
            connection = DriverManager.getConnection(jdbcUrl);
            statement = connection.createStatement();

            // Create a table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS subject (subjectId BIGINT, subjectName TEXT, teacherId BIGINT)";
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
                long teacherId = resultSet.getLong("subjectName");
                subjectData = new SubjectData(subjectId, teacherId, subjectName);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjectData;
    }

    public List<SubjectData> getSubjectsByTeacherId(long teacherId) {
        List<SubjectData> subjects = new ArrayList<>();

        try {
            String query = "SELECT * FROM subject WHERE teacherId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, teacherId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long subjectId = resultSet.getLong("subjectId");
                String subjectName = resultSet.getString("subjectName");
                SubjectData subject = new SubjectData(subjectId, teacherId, subjectName);
                subjects.add(subject);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return subjects;
    }
    public List<SubjectData> getSubjects() {
        List<SubjectData> subjects = new ArrayList<>();

        try {
            String query = "SELECT * FROM subject";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                long subjectId = resultSet.getInt("subjectId");
                String subjectName = resultSet.getString("subjectName");
                long teacherId = resultSet.getLong("teacherId");

                SubjectData subject = new SubjectData(subjectId, teacherId, subjectName);
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
            String query = "INSERT INTO subject (subjectId, subjectName, teacherId) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, subjectData.getSubjectId());
            preparedStatement.setString(2, subjectData.getSubjectName());
            preparedStatement.setLong(3, subjectData.getTeacherId());
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
