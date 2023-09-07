package com.example.studentsgradessystem.domain.usecases;

import com.example.studentsgradessystem.data.TeacherRepositoryImpl;
import com.example.studentsgradessystem.domain.pojo.Teacher;
import com.example.studentsgradessystem.domain.repositories.TeacherRepository;

public class InsertTeacherUseCase {

    private final TeacherRepository teacherRepository;

    public InsertTeacherUseCase(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void insertTeacher(Teacher teacher) {
        teacherRepository.insertNewTeacher(teacher);
    }
}