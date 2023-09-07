package com.example.studentsgradessystem.data;


import com.example.studentsgradessystem.data.database.Database;
import com.example.studentsgradessystem.data.mappers.Mapper;
import com.example.studentsgradessystem.domain.events.StudentObserver;
import com.example.studentsgradessystem.domain.repositories.StudentRepository;
import com.example.studentsgradessystem.domain.pojo.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    List<StudentObserver> observers = new ArrayList<>();
    private Database database;
    public StudentRepositoryImpl() {
        database = new Database();
    }

    @Override
    public void getAllStudents() {
        List<Student> list = Mapper.listStuToEntity(database.getAllStudents());
        notifyObservers(list);
    }

    @Override
    public void insertNewStudent(Student student) {
        if (database.insertNewStudent(Mapper.entityToStudentData(student))) {
            getAllStudents();
        }
    }

    @Override
    public void updateStudent(Student student) {
        if (database.updateStudent(Mapper.entityToStudentData(student))) {
            getAllStudents();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        if (database.deleteStudent(Mapper.entityToStudentData(student))) {
            getAllStudents();
        }
    }

    @Override
    public void registerObserver(StudentObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(StudentObserver observer) {
        observers.removeIf(o -> o.equals(observer));
    }

    @Override
    public void notifyObservers(List<Student> data) {
        for (StudentObserver o : observers) o.update(data);
    }
}
