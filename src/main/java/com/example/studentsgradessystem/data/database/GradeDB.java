package com.example.studentsgradessystem.data.database;

import com.example.studentsgradessystem.data.pojo.GradeData;
import com.example.studentsgradessystem.data.pojo.SubjectData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradeDB {

    private final String projectDir = System.getProperty("user.dir");
    private Connection connection = null;

    private SubjectDB subjectDB = null;

    public GradeDB(SubjectDB subjectDB) {

        this.subjectDB = subjectDB;

        try {
            // Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl = "jdbc:sqlite:" + projectDir + "/grade.db";
            connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();

            // Create a table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS grade (studentNumber BIGINT, subjectId BIGINT, grade BIGINT)";
            statement.execute(createTableQuery);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public List<GradeData> getGradesByNumber(long studentNumber) {
        List<GradeData> grades = new ArrayList<>();

        try {
            String query = "SELECT * FROM grade WHERE studentNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, studentNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long subjectId = resultSet.getLong("subjectId");
                long grade = resultSet.getLong("grade");

                SubjectData subjectData = subjectDB.getSubjectById(subjectId);

                GradeData gradeData = new GradeData(subjectData, studentNumber, grade);
                grades.add(gradeData);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grades;
    }

    public boolean insertGrade(GradeData gradeData) {
        try {
            String query = "INSERT INTO grade (studentNumber, subjectId, grade) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, gradeData.getStudentNumber());
            preparedStatement.setLong(2, gradeData.getSubject().getSubjectId());
            preparedStatement.setLong(3, gradeData.getStudentGrade());

            int rowsInserted = preparedStatement.executeUpdate();

            preparedStatement.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateGrade(GradeData gradeData) {
        try {
            String query = "UPDATE grade SET grade = ? WHERE studentNumber = ? AND subjectId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, gradeData.getStudentGrade());
            preparedStatement.setLong(2, gradeData.getStudentNumber());
            preparedStatement.setLong(3, gradeData.getSubject().getSubjectId());

            int rowsUpdated = preparedStatement.executeUpdate();

            preparedStatement.close();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public void deleteGradesByNumber(long studentNumber) {
        try {
            String query = "DELETE FROM grade WHERE studentNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, studentNumber);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

