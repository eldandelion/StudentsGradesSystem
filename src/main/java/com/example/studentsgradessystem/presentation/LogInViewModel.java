package com.example.studentsgradessystem.presentation;

import com.example.studentsgradessystem.data.TeacherRepositoryImpl;
import com.example.studentsgradessystem.domain.events.TeacherObserver;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Subject;
import com.example.studentsgradessystem.domain.pojo.Teacher;
import com.example.studentsgradessystem.domain.repositories.TeacherRepository;
import com.example.studentsgradessystem.domain.usecases.GetTeacherByCredentialsUseCase;
import com.example.studentsgradessystem.domain.usecases.InsertTeacherUseCase;
import com.example.studentsgradessystem.domain.usecases.RegisterTeacherObserverUseCase;
import com.example.studentsgradessystem.domain.usecases.UnregisterTeacherObserverUseCase;

import java.util.ArrayList;
import java.util.List;

public class LogInViewModel {


    private final TeacherRepository teacherRepository = new TeacherRepositoryImpl();
    private final GetTeacherByCredentialsUseCase getTeacherByCredentialsUseCase = new GetTeacherByCredentialsUseCase(teacherRepository);
    private final RegisterTeacherObserverUseCase registerTeacherObserverUseCase = new RegisterTeacherObserverUseCase(teacherRepository);
    private final InsertTeacherUseCase insertTeacherUseCase = new InsertTeacherUseCase(teacherRepository);
    private final UnregisterTeacherObserverUseCase unregisterTeacherObserverUseCase = new UnregisterTeacherObserverUseCase(teacherRepository);
    public void getTeacherByCredentials(String username, String password) {
        getTeacherByCredentialsUseCase.getTeacherByCredentials(username, password);
    }

    public void registerObserver(TeacherObserver observer) {
        registerTeacherObserverUseCase.registerObserver(observer);
    }

    public void registerTeacher(String username, String email, String password) {
        insertTeacherUseCase.insertTeacher(new Teacher(username, email, password));
    }

    public void unregisterObserver(TeacherObserver observer) {
        unregisterTeacherObserverUseCase.unregisterObserver(observer);
    }






}
