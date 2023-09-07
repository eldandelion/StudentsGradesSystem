package com.example.studentsgradessystem.domain.usecases;

import com.example.studentsgradessystem.domain.events.TeacherObserver;
import com.example.studentsgradessystem.domain.repositories.TeacherRepository;

public class RegisterTeacherObserverUseCase {

    private final TeacherRepository teacherRepository;

    public RegisterTeacherObserverUseCase(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void registerObserver(TeacherObserver observer) {
        teacherRepository.registerObserver(observer);
    }
}
