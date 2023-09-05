package com.example.studentsgradessystem.data;

import com.example.studentsgradessystem.domain.repositories.StudentRepository;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.react.Observer;

import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public List<Student> getAllStudents() {
        return null;
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
    public void registerObserver(Observer observer) {

    }

    @Override
    public void unregisterObserver(Observer observer) {

    }

    @Override
    public void notifyObservers() {

    }
}
