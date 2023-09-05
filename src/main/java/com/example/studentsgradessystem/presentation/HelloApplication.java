package com.example.studentsgradessystem.presentation;


import com.example.studentsgradessystem.data.StudentRepositoryImpl;
import com.example.studentsgradessystem.data.database.SubjectDB;
import com.example.studentsgradessystem.domain.events.StudentObserver;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.repositories.StudentRepository;
import javafx.application.Application;
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

        SubjectDB subjectDB = new SubjectDB();

        List<Student> studentList = new ArrayList<>();

        StudentRepository studentRepository = new StudentRepositoryImpl();


        studentRepository.registerObserver(new StudentObserver() {
            @Override
            public void update(List<Student> data) {
                studentList.addAll(data);
                for (Student s : data) {
                    System.out.println(s.getStudentName());
                }
            }
        });

        studentRepository.getAllStudents();
        studentRepository.deleteStudent(studentList.get(0));


        launch();
    }
}