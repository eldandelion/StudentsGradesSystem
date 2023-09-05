package com.example.studentsgradessystem.data;

import com.example.studentsgradessystem.domain.pojo.Subject;

public class GradeData {


    private SubjectData subject;
    private long studentNumber;
    private long studentGrade;

    public GradeData(SubjectData subject, long studentNumber, long studentGrade) {
        this.subject = subject;
        this.studentNumber = studentNumber;
        this.studentGrade = studentGrade;
    }

    public SubjectData getSubject() {
        return subject;
    }

    public void setSubject(SubjectData subject) {
        this.subject = subject;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(long studentNumber) {
        this.studentNumber = studentNumber;
    }

    public long getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(long studentGrade) {
        this.studentGrade = studentGrade;
    }
}
