package com.example.studentsgradessystem.data.database;

import com.example.studentsgradessystem.data.pojo.GradeData;
import com.example.studentsgradessystem.data.pojo.StudentData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StudentDB {

    private final String projectDir = System.getProperty("user.dir");
    private final Connection connection;

    private final GradeDB gradeDB;

    public StudentDB(GradeDB gradeDB) {

        this.gradeDB = gradeDB;

        try {
            // Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl = "jdbc:sqlite:" + projectDir + "/student.db";
            connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();

            // Create a table
            String createTableQuery = "CREATE TABLE IF NOT EXISTS student (studentNumber INTEGER PRIMARY KEY, studentName TEXT)";
            statement.execute(createTableQuery);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean insertNewStudent(StudentData studentData) {
        try {
            String query = "INSERT INTO student (studentNumber, studentName) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, studentData.getStudentId());
            preparedStatement.setString(2, studentData.getStudentName());

            int rowsInserted = preparedStatement.executeUpdate();

            preparedStatement.close();

            // If student insertion is successful, also insert grades for the student
            if (rowsInserted > 0) {
                for (GradeData gradeData : studentData.getGrades()) {
                    gradeDB.insertGrade(gradeData);
                }
            }

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<StudentData> getAllStudents() {
        List<StudentData> students = new ArrayList<>();

        try {
            String query = "SELECT * FROM student";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long studentNumber = resultSet.getLong("studentNumber");
                String studentName = resultSet.getString("studentName");

                List<GradeData> grades = gradeDB.getGradesByNumber(studentNumber);

                StudentData studentData = new StudentData(studentNumber, studentName, grades);
                students.add(studentData);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    public boolean updateStudent(StudentData studentData) {
        try {
            String query = "UPDATE student SET studentName = ? WHERE studentNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentData.getStudentName());
            preparedStatement.setLong(2, studentData.getStudentId());

            int rowsUpdated = preparedStatement.executeUpdate();

            preparedStatement.close();

            if (rowsUpdated > 0) {
                // If student update is successful, also update the grades for the student
                for (GradeData gradeData : studentData.getGrades()) {
                    gradeDB.updateGrade(gradeData);
                }
            }

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteStudent(StudentData studentData) {
        try {
            String query = "DELETE FROM student WHERE studentNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, studentData.getStudentId());

            int rowsDeleted = preparedStatement.executeUpdate();

            preparedStatement.close();

            if (rowsDeleted > 0) {
                // If student deletion is successful, also delete the grades for the student
                gradeDB.deleteGradesByNumber(studentData.getStudentId());
            }

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
