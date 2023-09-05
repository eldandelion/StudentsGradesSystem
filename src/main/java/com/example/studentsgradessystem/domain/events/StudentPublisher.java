package com.example.studentsgradessystem.domain.events;

import com.example.studentsgradessystem.domain.pojo.Student;

import java.util.List;

//Implemented by Repository
//When data is updated in the SQL database,
// Repository notifies UI with fresh data
public interface StudentPublisher {

    void registerObserver(StudentObserver observer);
    void unregisterObserver(StudentObserver observer);
    void notifyObservers(List<Student> data);

}
