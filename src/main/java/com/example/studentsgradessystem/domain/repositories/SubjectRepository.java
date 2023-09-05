package com.example.studentsgradessystem.domain.repositories;

import com.example.studentsgradessystem.domain.events.SubjectPublisher;
import com.example.studentsgradessystem.domain.pojo.Subject;

import java.util.List;

public interface SubjectRepository extends SubjectPublisher {

    void getAllSubjects();

    boolean insertNewSubject(Subject subject);

    boolean deleteSubject(long subjectId);

    boolean updateSubject(Subject subject);


}
