package com.example.studentsgradessystem.domain.pojo;

import java.util.List;


//Everything that should be inside the student SQL table
public class Student {

    private long studentId;
    private String studentName;
    private List<Grade> grades;

    public Student(long studentId, String studentName, List<Grade> grades) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grades = grades;
    }

    public Student() {
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
}
