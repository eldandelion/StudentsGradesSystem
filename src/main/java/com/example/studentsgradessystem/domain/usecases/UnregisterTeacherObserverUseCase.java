package com.example.studentsgradessystem.domain.usecases;

import com.example.studentsgradessystem.data.TeacherRepositoryImpl;
import com.example.studentsgradessystem.domain.events.TeacherObserver;
import com.example.studentsgradessystem.domain.repositories.TeacherRepository;

public class UnregisterTeacherObserverUseCase {

    TeacherRepository repository;

    public UnregisterTeacherObserverUseCase(TeacherRepository repository) {
        this.repository = repository;
    }

    public void unregisterObserver(TeacherObserver observer) {
        repository.unregisterObserver(observer);
    }
}
