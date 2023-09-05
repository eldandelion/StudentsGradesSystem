package com.example.studentsgradessystem.data;

import com.example.studentsgradessystem.domain.pojo.Grade;

import java.util.List;

public class StudentData {


    private long studentId;
    private String studentName;
    private List<GradeData> grades;

    public StudentData(long studentId, String studentName, List<GradeData> grades) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.grades = grades;
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

    public List<GradeData> getGrades() {
        return grades;
    }

    public void setGrades(List<GradeData> grades) {
        this.grades = grades;
    }
}
