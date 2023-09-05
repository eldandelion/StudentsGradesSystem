package com.example.studentsgradessystem.data;


import com.example.studentsgradessystem.data.database.GradeDB;
import com.example.studentsgradessystem.data.database.StudentDB;
import com.example.studentsgradessystem.data.database.SubjectDB;
import com.example.studentsgradessystem.data.mappers.Mapper;
import com.example.studentsgradessystem.domain.repositories.StudentRepository;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.react.Observer;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    List<Observer> observers = new ArrayList<>();
    private StudentDB studentDB;
    private GradeDB gradeDB;
    private SubjectDB subjectDB;
    public StudentRepositoryImpl() {
        subjectDB = new SubjectDB();
        gradeDB = new GradeDB(subjectDB);
        studentDB = new StudentDB(gradeDB);
    }

    @Override
    public void getAllStudents() {
        List<Student> list = Mapper.listStuToEntity(studentDB.getAllStudents());
        notifyObservers(list);
    }

    @Override
    public void insertNewStudent(Student student) {
        if (studentDB.insertNewStudent(Mapper.entityToStudentData(student))) {
            notifyObservers();
        }
    }

    @Override
    public void updateStudent(Student student) {
        if (studentDB.updateStudent(Mapper.entityToStudentData(student))) {
            getAllStudents();
        }
    }

    @Override
    public void deleteStudent(Student student) {
        if (studentDB.deleteStudent(Mapper.entityToStudentData(student))) {
            getAllStudents();
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        observers.removeIf(o -> o.equals(observer));
    }

    @Override
    public void notifyObservers(List<Student> data) {
        for (Observer o : observers) o.update(data);
    }
}
