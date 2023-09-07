package com.example.studentsgradessystem.presentation;


import com.example.studentsgradessystem.data.StudentRepositoryImpl;
import com.example.studentsgradessystem.data.database.Database;
import com.example.studentsgradessystem.domain.events.StudentObserver;
import com.example.studentsgradessystem.domain.pojo.Grade;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Subject;
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


        Database database = new Database();


        List<Student> studentList = new ArrayList<>();

        StudentRepository studentRepository = new StudentRepositoryImpl();

        List<Grade> list = new ArrayList<>();
        Subject subject = new Subject(1232, 1, "Math");

        list.add(new Grade(subject, 14, 23));
        studentRepository.insertNewStudent(new Student(14, "Daniel", list));

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
//        studentRepository.deleteStudent(studentList.get(0));


        launch();
    }
}