package com.example.studentsgradessystem.domain.usecases;

import com.example.studentsgradessystem.domain.repositories.TeacherRepository;

public class GetTeacherByCredentialsUseCase {

    TeacherRepository repository;

    public GetTeacherByCredentialsUseCase(TeacherRepository repository) {
        this.repository = repository;
    }

    public void getTeacherByCredentials(String username, String password) {
        repository.getTeacherByCredentials(username, password);
    }
}
