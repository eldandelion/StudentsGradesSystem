package com.example.studentsgradessystem.domain.pojo;


//Stored in a separate SQL table
//So users can separately manage available subjects in the system
public class Subject {
    private long subjectId;
    private long teacherId;
    private String subjectName;

    public Subject(long subjectId, long teacherId, String subjectName) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.subjectName = subjectName;
    }

    public Subject() {
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
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
