package com.example.studentsgradessystem.domain.pojo;


//Stored in a separate SQL table
//So users can separately manage available subjects in the system
public class Subject {
    private long subjectId;
    private String subjectName;

    public Subject(long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Subject() {
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
