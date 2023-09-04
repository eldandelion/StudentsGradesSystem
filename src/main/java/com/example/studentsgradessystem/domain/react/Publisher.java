package com.example.studentsgradessystem.domain.react;

//Implemented by Repository
//When data is updated in the SQL database,
// Repository notifies UI with fresh data
public interface Publisher {

    void registerObserver(Observer observer);
    void unregisterObserver(Observer observer);
    void notifyObservers();

}
