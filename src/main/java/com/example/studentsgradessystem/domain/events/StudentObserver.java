package com.example.studentsgradessystem.domain.events;

import com.example.studentsgradessystem.domain.pojo.Student;

import java.util.List;

//View implements observer, if data has been updated, repository,
// which implements Publisher sends updated data to Observer
public interface StudentObserver {
    void update(List<Student> data);
}
