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

    public TeacherData getTeacherByCredentials(String email, String password) {
        try {
            String query = "SELECT * FROM teacher WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long teacherId = resultSet.getLong("teacherId");
                String name = resultSet.getString("name");
                String studentIds = resultSet.getString("studentIds");

                List<StudentData> studentData = studentDB.getAllStudents();
                List<StudentData> matchingStudents = new ArrayList<>();

                List<SubjectData> subjectData = subjectDB.getSubjectsByTeacherId(teacherId);


                String[] studentIdArray = studentIds.split(",");
                for (String studentId : studentIdArray) {
                    long id = Long.parseLong(studentId.trim());
                    for (StudentData student : studentData) {
                        if (student.getStudentId() == id) {
                            matchingStudents.add(student);
                            break;
                        }
                    }
                }

                TeacherData teacher = new TeacherData();
                teacher.setTeacherId(teacherId);
                teacher.setEmail(email);
                teacher.setName(name);
                teacher.setPassword(password);
                teacher.setStudents(matchingStudents);
                teacher.setSubjects(subjectData);
                return teacher;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving teacher by credentials", e);
        }

        return null; // Return null if no teacher found with the given credentials
    }


    public List<TeacherData> getAllTeachers() {
        List<TeacherData> teachers = new ArrayList<>();

        try {
            String query = "SELECT * FROM teacher";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set and populate the teachers list
            while (resultSet.next()) {
                // Retrieve teacher data from the result set
                long teacherId = resultSet.getLong("teacherId");
                String email = resultSet.getString("email");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String studentIds = resultSet.getString("studentIds");

                // Retrieve associated students and subjects using their respective DB objects
                List<StudentData> studentData = studentDB.getAllStudents();
                List<StudentData> matchingStudents = new ArrayList<>();
                List<SubjectData> subjectData = subjectDB.getSubjectsByTeacherId(teacherId);

                // Process studentIds to get matching students
                String[] studentIdArray = studentIds.split(",");
                for (String studentId : studentIdArray) {
                    long id = Long.parseLong(studentId.trim());
                    for (StudentData student : studentData) {
                        if (student.getStudentId() == id) {
                            matchingStudents.add(student);
                            break;
                        }
                    }
                }

                // Create TeacherData object and set its properties
                TeacherData teacher = new TeacherData();
                teacher.setTeacherId(teacherId);
                teacher.setEmail(email);
                teacher.setName(name);
                teacher.setPassword(password);
                teacher.setStudents(matchingStudents);
                teacher.setSubjects(subjectData);

                // Add the teacher to the list
                teachers.add(teacher);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all teachers", e);
        }

        return teachers;
    }

    public boolean updateTeacher(TeacherData teacher) {
        try {
            String query = "UPDATE teacher SET email = ?, name = ?, password = ?, studentIds = ? WHERE teacherId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getEmail());
            preparedStatement.setString(2, teacher.getName());
            preparedStatement.setString(3, teacher.getPassword());

            // Convert the list of students to a comma-separated string of student IDs
            List<StudentData> students = teacher.getStudents();
            StringBuilder studentIdsBuilder = new StringBuilder();
            for (int i = 0; i < students.size(); i++) {
                studentIdsBuilder.append(students.get(i).getStudentId());
                if (i < students.size() - 1) {
                    studentIdsBuilder.append(",");
                }
            }
            preparedStatement.setString(4, studentIdsBuilder.toString());
            preparedStatement.setLong(5, teacher.getTeacherId());

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            // Return true if at least one row was affected, indicating a successful update
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error updating teacher", e);
        }
    }


    public boolean insertNewTeacher(TeacherData teacher) {
        try {
            String query = "INSERT INTO teacher (teacherId, email, name, password, studentIds, subjectIds) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, teacher.getTeacherId());
            preparedStatement.setString(2, teacher.getEmail());
            preparedStatement.setString(3, teacher.getName());
            preparedStatement.setString(4, teacher.getPassword());

            // Convert the list of students to a comma-separated string of student IDs
            List<StudentData> students = teacher.getStudents();
            StringBuilder studentIdsBuilder = new StringBuilder();
            for (int i = 0; i < students.size(); i++) {
                studentIdsBuilder.append(students.get(i).getStudentId());
                if (i < students.size() - 1) {
                    studentIdsBuilder.append(",");
                }
            }
            preparedStatement.setString(5, studentIdsBuilder.toString());

            // Convert the list of subjects to a comma-separated string of subject IDs
            List<SubjectData> subjects = teacher.getSubjects();
            StringBuilder subjectIdsBuilder = new StringBuilder();
            for (int i = 0; i < subjects.size(); i++) {
                subjectIdsBuilder.append(subjects.get(i).getSubjectId());
                if (i < subjects.size() - 1) {
                    subjectIdsBuilder.append(",");
                }
            }
            preparedStatement.setString(6, subjectIdsBuilder.toString());

            // Execute the insert query
            int rowsAffected = preparedStatement.executeUpdate();

            // Return true if at least one row was affected, indicating a successful insertion
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting new teacher", e);
        }
    }

    public boolean deleteTeacherById(long teacherId) {
        try {
            String query = "DELETE FROM teacher WHERE teacherId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, teacherId);

            // Execute the delete query
            int rowsAffected = preparedStatement.executeUpdate();

            // Return true if at least one row was affected, indicating a successful deletion
            return rowsAffected > 0;
        } catch (SQLException e) {
            return false;
        }
    }


}
