package com.example.studentsgradessystem.data;


import com.example.studentsgradessystem.domain.repositories.StudentRepository;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.events.StudentObserver;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {



    @Override
    public void getAllStudents() {


    }

    @Override
    public void insertNewStudent(Student student) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }

    @Override
    public void registerObserver(StudentObserver observer) {

    }

    @Override
    public void unregisterObserver(StudentObserver observer) {

    }

    @Override
    public void notifyObservers(List<Student> data) {

    }
}
