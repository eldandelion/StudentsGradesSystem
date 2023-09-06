package com.example.studentsgradessystem.data.pojo;

public class SubjectData {

    private long subjectId;
    private long teacherId;
    private String subjectName;

    public SubjectData(long subjectId, long teacherId, String subjectName) {
        this.subjectId = subjectId;
        this.teacherId = teacherId;
        this.subjectName = subjectName;
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
