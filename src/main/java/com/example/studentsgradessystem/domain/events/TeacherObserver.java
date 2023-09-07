package com.example.studentsgradessystem.domain.events;

import com.example.studentsgradessystem.domain.Flags;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Teacher;

import java.util.List;

public interface TeacherObserver {
    void update(List<Teacher> data);
    void update(Teacher data);
    void update(Flags flag);

}
