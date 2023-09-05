package com.example.studentsgradessystem.domain.react;

import com.example.studentsgradessystem.domain.pojo.Student;

import java.util.List;

//View implements observer, if data has been updated, repository,
// which implements Publisher sends updated data to Observer
public interface Observer {
    void update(List<Student> data);
}
