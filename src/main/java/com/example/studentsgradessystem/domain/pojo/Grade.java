package com.example.studentsgradessystem.domain.pojo;


//Contains information about grade
//It is stored in a separate SQL table
public class Grade {

    private Subject subject;
    private long studentNumber;
    private long studentGrade;

    public Grade(Subject subject, long studentNumber, long studentGrade) {
        this.subject = subject;
        this.studentNumber = studentNumber;
        this.studentGrade = studentGrade;
    }

    public Grade() {
    }

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
