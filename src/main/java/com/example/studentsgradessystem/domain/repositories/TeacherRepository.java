package com.example.studentsgradessystem.domain.repositories;

import com.example.studentsgradessystem.domain.events.TeacherPublisher;
import com.example.studentsgradessystem.domain.pojo.Teacher;

public interface TeacherRepository extends TeacherPublisher {

    void getAllTeachers();
    void getTeacherByCredentials(String email, String password);
    void insertNewTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);


}
