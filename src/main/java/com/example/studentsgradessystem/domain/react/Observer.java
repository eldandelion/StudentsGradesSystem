package com.example.studentsgradessystem.domain.react;

//View implements observer, if data has been updated, repository,
// which implements Publisher sends updated data to Observer
public interface Observer {
    void update();
}
