package com.example.studentsgradessystem.data.database;

import com.example.studentsgradessystem.data.pojo.GradeData;
import com.example.studentsgradessystem.data.pojo.StudentData;
import com.example.studentsgradessystem.data.pojo.SubjectData;
import com.example.studentsgradessystem.data.pojo.TeacherData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private final String projectDir = System.getProperty("user.dir");
    private final Connection connection;
    private final Statement statement;
    public Database() {

        try {
            // Load the SQLite JDBC driver class
            Class.forName("org.sqlite.JDBC");
            String jdbcUrl = "jdbc:sqlite:" + projectDir + "/data.db";
            connection = DriverManager.getConnection(jdbcUrl);
            statement = connection.createStatement();

            // Create tables if they don't exist
            String createSubjectTableQuery = "CREATE TABLE IF NOT EXISTS subject (subjectId BIGINT, subjectName TEXT, teacherId BIGINT)";
            String createGradeTableQuery = "CREATE TABLE IF NOT EXISTS grade (studentNumber BIGINT, subjectId BIGINT, grade BIGINT)";
            String createStudentTableQuery = "CREATE TABLE IF NOT EXISTS student (studentNumber INTEGER PRIMARY KEY, studentName TEXT)";
            String createTeacherTableQuery = "CREATE TABLE IF NOT EXISTS teacher (teacherId INTEGER PRIMARY KEY, email TEXT, name TEXT, password TEXT, studentIds TEXT, subjectIds TEXT)";
            statement.execute(createSubjectTableQuery);
            statement.execute(createGradeTableQuery);
            statement.execute(createStudentTableQuery);
            statement.execute(createTeacherTableQuery);

            // Create additional tables if necessary (e.g., subjects, students)

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /** This part is for subject table **/
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
            // Check if the subject already exists
            SubjectData existingSubject = getSubjectById(subjectData.getSubjectId());

            if (existingSubject != null) {
                // Subject already exists, update the data
                String query = "UPDATE subject SET subjectName = ?, teacherId = ? WHERE subjectId = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, subjectData.getSubjectName());
                preparedStatement.setLong(2, subjectData.getTeacherId());
                preparedStatement.setLong(3, subjectData.getSubjectId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } else {
                // Subject does not exist, insert new data
                String query = "INSERT INTO subject (subjectId, subjectName, teacherId) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setLong(1, subjectData.getSubjectId());
                preparedStatement.setString(2, subjectData.getSubjectName());
                preparedStatement.setLong(3, subjectData.getTeacherId());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            }
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

    /** This part is for grade table **/
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

                SubjectData subjectData = getSubjectById(subjectId);
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

    /** This part is for student table **/
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
                    insertGrade(gradeData);
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

                List<GradeData> grades = getGradesByNumber(studentNumber);

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
                    updateGrade(gradeData);
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
                deleteGradesByNumber(studentData.getStudentId());
            }

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /** This part is for teacher table **/
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

                List<StudentData> studentData = getAllStudents();
                List<StudentData> matchingStudents = new ArrayList<>();

                List<SubjectData> subjectData = getSubjectsByTeacherId(teacherId);


                if (!studentIds.isEmpty()) {
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
                List<StudentData> studentData = getAllStudents();
                List<StudentData> matchingStudents = new ArrayList<>();
                List<SubjectData> subjectData = getSubjectsByTeacherId(teacherId);

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

    public TeacherData getTeacherByEmail(String email) {
        try {
            String query = "SELECT * FROM teacher WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve teacher data from the result set
                long teacherId = resultSet.getLong("teacherId");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String studentIds = resultSet.getString("studentIds");

                // Retrieve associated students and subjects using their respective DB objects
                List<StudentData> studentData = getAllStudents();
                List<StudentData> matchingStudents = new ArrayList<>();
                List<SubjectData> subjectData = getSubjectsByTeacherId(teacherId);

                // Process studentIds to get matching students
                if (!studentIds.isEmpty()) {
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
                }


                // Create TeacherData object and set its properties
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
            throw new RuntimeException("Error retrieving teacher by email", e);
        }

        return null; // Return null if no teacher with the specified email is found
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
            String query = "INSERT INTO teacher (email, name, password, studentIds, subjectIds) VALUES (?, ?, ?, ?, ?)";
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

            // Convert the list of subjects to a comma-separated string of subject IDs
            List<SubjectData> subjects = teacher.getSubjects();
            StringBuilder subjectIdsBuilder = new StringBuilder();
            for (int i = 0; i < subjects.size(); i++) {
                subjectIdsBuilder.append(subjects.get(i).getSubjectId());
                if (i < subjects.size() - 1) {
                    subjectIdsBuilder.append(",");
                }
            }
            preparedStatement.setString(5, subjectIdsBuilder.toString());

            for (SubjectData s : teacher.getSubjects()) {
                insertSubject(s);
            }

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
