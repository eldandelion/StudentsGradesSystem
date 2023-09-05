package com.example.studentsgradessystem.domain.repositories;

import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.react.Observer;
import com.example.studentsgradessystem.domain.react.Publisher;

import java.util.List;

public interface StudentRepository extends Publisher {

    List<Student> getAllStudents();
    void insertNewStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);


}
