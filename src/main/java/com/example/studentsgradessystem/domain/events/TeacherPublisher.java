package com.example.studentsgradessystem.domain.events;

import com.example.studentsgradessystem.domain.Flags;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Teacher;

import java.util.List;

public interface TeacherPublisher {


    void registerObserver(TeacherObserver observer);
    void unregisterObserver(TeacherObserver observer);
    void notifyObservers(List<Teacher> data);
    void notifyObservers(Teacher data);
    void notifyObservers(Flags flag);
}
