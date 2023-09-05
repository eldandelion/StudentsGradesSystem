package com.example.studentsgradessystem.domain.events;

import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Subject;

import java.util.List;

public interface SubjectPublisher {

    void registerObserver(StudentObserver observer);
    void unregisterObserver(StudentObserver observer);
    void notifyObservers(List<Subject> data);
}
