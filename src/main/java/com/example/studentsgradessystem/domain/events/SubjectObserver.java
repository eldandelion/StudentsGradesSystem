package com.example.studentsgradessystem.domain.events;

import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Subject;

import java.util.List;

public interface SubjectObserver {

    void update(List<Subject> data);


}
