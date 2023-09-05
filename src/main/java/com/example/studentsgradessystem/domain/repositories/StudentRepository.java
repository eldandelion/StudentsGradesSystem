package com.example.studentsgradessystem.domain.repositories;

import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.events.StudentPublisher;

public interface StudentRepository extends StudentPublisher {

    void getAllStudents();
    void insertNewStudent(Student student);
    void updateStudent(Student student);
    void deleteStudent(Student student);

}
