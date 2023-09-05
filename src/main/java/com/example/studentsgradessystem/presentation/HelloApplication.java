package com.example.studentsgradessystem.presentation;

import com.example.studentsgradessystem.data.GradeData;
import com.example.studentsgradessystem.data.StudentRepositoryImpl;
import com.example.studentsgradessystem.data.SubjectData;
import com.example.studentsgradessystem.data.database.GradeDB;
import com.example.studentsgradessystem.data.database.StudentDB;
import com.example.studentsgradessystem.data.database.SubjectDB;
import com.example.studentsgradessystem.domain.pojo.Grade;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Subject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("Hello!");
        stage.show();
    }

    public static void main(String[] args) {

//        SubjectDB subjectDB = new SubjectDB();
//
//        subjectDB.insertSubject(new SubjectData(32, "math"));
//        List<SubjectData> data = subjectDB.getSubjects();

//        SubjectData subjectData = new SubjectData(32, "math");

//        GradeDB gradeDB = new GradeDB(subjectDB);

        StudentRepositoryImpl studentRepository = new StudentRepositoryImpl();
        List<Grade> grades = new ArrayList<>();
        grades.add(new Grade(new Subject(32, "math"), 21, 22));

        studentRepository.insertNewStudent(new Student(31, "Max", grades));
        List<Student> list = studentRepository.getAllStudents();



        for (Student student : list) {
            System.out.println(student.getStudentName());
        }
//        gradeDB.insertGrade(new GradeData(subjectData, 34243, 22));

//        List<GradeData> data = gradeDB.getGradesByNumber(34243);
//        for (GradeData gradeData : data) {
//            System.out.println(gradeData.getStudentGrade());
//            System.out.println(gradeData.getSubject().getSubjectName());
//        }
        launch();
    }
}