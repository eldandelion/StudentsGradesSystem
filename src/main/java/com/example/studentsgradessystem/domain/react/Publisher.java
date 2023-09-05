package com.example.studentsgradessystem.domain.react;

import com.example.studentsgradessystem.domain.pojo.Student;

import java.util.List;

//Implemented by Repository
//When data is updated in the SQL database,
// Repository notifies UI with fresh data
public interface Publisher {

    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers(List<Student> data);

}
