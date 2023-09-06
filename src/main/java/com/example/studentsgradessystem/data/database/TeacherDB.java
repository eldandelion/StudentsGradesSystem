package com.example.studentsgradessystem.data.database;

import com.example.studentsgradessystem.data.pojo.StudentData;
import com.example.studentsgradessystem.data.pojo.SubjectData;
import com.example.studentsgradessystem.data.pojo.TeacherData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDB {

    private final String projectDir = System.getProperty("user.dir");
    private final Connection connection;
    private final Statement statement;
    private final StudentDB studentDB;
    private final SubjectDB subjectDB;

    public TeacherDB(StudentDB studentDB, SubjectDB subjectDB) {
        this.studentDB = studentDB;
        this.subjectDB = subjectDB;

        try {
            // Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl = "jdbc:sqlite:" + projectDir + "/teacher.db";
            connection = DriverManager.getConnection(jdbcUrl);
            statement = connection.createStatement();

            // Create tables if they don't exist
            String createTeacherTableQuery = "CREATE TABLE IF NOT EXISTS teacher (teacherId BIGINT, email TEXT, name TEXT, password TEXT, studentIds TEXT, subjectIds TEXT)";
            statement.execute(createTeacherTableQuery);

            // Create additional tables if necessary (e.g., subjects, students)

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    public TeacherData getTeacherByCredentials(String email, String password){
//        try {
//            String query = "SELECT * FROM teacher WHERE email = ? AND password = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            if (resultSet.next()) {
//                long teacherId = resultSet.getLong("teacherId");
//                String name = resultSet.getString("name");
//                String studentIds = resultSet.getString("studentIds");
//
//                List<StudentData> studentData = studentDB.getAllStudents();
//                List<StudentData> matchingStudents = new ArrayList<>();
//
//                List<SubjectData>
//
//                String[] studentIdArray = studentIds.split(",");
//                for (String studentId : studentIdArray) {
//                    long id = Long.parseLong(studentId.trim());
//                    for (StudentData student : studentData) {
//                        if (student.getStudentId() == id) {
//                            matchingStudents.add(student);
//                            break;
//                        }
//                    }
//                }
//
//                TeacherData teacher = new TeacherData();
//                teacher.setTeacherId(teacherId);
//                teacher.setEmail(email);
//                teacher.setName(name);
//                teacher.setPassword(password);
//                teacher.setStudents(matchingStudents);
//                teacher.setSubjects();
//                return teacher;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException("Error retrieving teacher by credentials", e);
//        }
//
//        return null; // Return null if no teacher found with the given credentials
//    }
//
//
//    public List<TeacherData> getAllTeachers() {
//
//    }
//    public boolean updateTeacher(TeacherDB teacher) {
//
//    }
//
//    public boolean insertNewTeacher(TeacherDB teacher) {
//
//    }
//
//    public boolean deleteTeacherById(long teacherId) {
//
//    }




}
