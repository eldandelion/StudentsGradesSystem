package com.example.studentsgradessystem.data;

import com.example.studentsgradessystem.data.database.Database;
import com.example.studentsgradessystem.data.mappers.Mapper;
import com.example.studentsgradessystem.data.pojo.TeacherData;
import com.example.studentsgradessystem.domain.Flags;
import com.example.studentsgradessystem.domain.events.TeacherObserver;
import com.example.studentsgradessystem.domain.pojo.Teacher;
import com.example.studentsgradessystem.domain.repositories.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryImpl implements TeacherRepository {

    List<TeacherObserver> observers = new ArrayList<>();
    private Database database;

    public TeacherRepositoryImpl() {
        database = new Database();
    }

    @Override
    public void registerObserver(TeacherObserver observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(TeacherObserver observer) {
        observers.removeIf(o -> o.equals(observer));
    }

    @Override
    public void notifyObservers(List<Teacher> data) {
        for (TeacherObserver o : observers) {
            o.update(data);
        }
    }

    @Override
    public void notifyObservers(Teacher data) {
        for (TeacherObserver o : observers) {
            o.update(data);
        }
    }

    @Override
    public void notifyObservers(Flags flag) {
        for (TeacherObserver o : observers) {
            o.update(flag);
        }
    }

    @Override
    public void getAllTeachers() {
        List<Teacher> teacherList = Mapper.teacherDataToEntityList(database.getAllTeachers());
        notifyObservers(teacherList);
    }

    @Override
    public void getTeacherByCredentials(String email, String password) {
        TeacherData teacher = database.getTeacherByCredentials(email, password);
        if (teacher != null) {
            notifyObservers(Flags.LogInSuccess);
            notifyObservers(Mapper.teacherDataToEntity(teacher));
        } else {
            notifyObservers(Flags.LogInFail);
        }
    }

    @Override
    public void insertNewTeacher(Teacher teacher) {
        if (database.insertNewTeacher(Mapper.teacherEntityToData(teacher))) {
            TeacherData teacherData = database.getTeacherByEmail(teacher.getEmail());
            if (teacherData != null) {
                notifyObservers(Mapper.teacherDataToEntity(teacherData));
                notifyObservers(Flags.RegisterSuccess);
            } else {
                notifyObservers(Flags.RegisterFail);
            }
        } else {
            notifyObservers(Flags.RegisterFail);
        }
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        if (database.updateTeacher(Mapper.teacherEntityToData(teacher))) getAllTeachers();
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        if (database.deleteTeacherById(teacher.getTeacherId())) getAllTeachers();
    }
}
