package com.example.studentsgradessystem.domain.events;

import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Teacher;

import java.util.List;

public interface TeacherPublisher {


    void registerObserver(StudentObserver observer);
    void unregisterObserver(StudentObserver observer);
    void notifyObservers(List<Teacher> data);
    void notifyObservers(Teacher data);
}
