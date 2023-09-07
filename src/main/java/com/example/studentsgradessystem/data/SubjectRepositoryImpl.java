package com.example.studentsgradessystem.data;

import com.example.studentsgradessystem.data.database.Database;
import com.example.studentsgradessystem.domain.events.StudentObserver;
import com.example.studentsgradessystem.domain.pojo.Subject;
import com.example.studentsgradessystem.domain.repositories.SubjectRepository;

import java.util.List;

public class SubjectRepositoryImpl implements SubjectRepository {


    private final Database database;

    public SubjectRepositoryImpl() {
        database = new Database();
    }

    @Override
    public void registerObserver(StudentObserver observer) {

    }

    @Override
    public void unregisterObserver(StudentObserver observer) {

    }

    @Override
    public void notifyObservers(List<Subject> data) {

    }

    @Override
    public void getAllSubjects() {

    }

    @Override
    public boolean insertNewSubject(Subject subject) {
        return false;
    }

    @Override
    public boolean deleteSubject(long subjectId) {
        return false;
    }

    @Override
    public boolean updateSubject(Subject subject) {
        return false;
    }
}
