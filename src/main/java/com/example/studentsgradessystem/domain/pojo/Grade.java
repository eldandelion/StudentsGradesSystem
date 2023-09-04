package com.example.studentsgradessystem.domain.pojo;


//Contains information about grade
//It is stored in a separate SQL table
public class Grade {


    Subject subject;
    long studentNumber;
    long studentGrade;

    public Subject getSubject() {
        return subject;
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
