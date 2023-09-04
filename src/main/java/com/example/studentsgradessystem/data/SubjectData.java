package com.example.studentsgradessystem.data;

public class SubjectData {

<<<<<<< HEAD
    private long subjectId;
    private String subjectName;
=======
    long subjectId;
    String subjectName;
>>>>>>> 73ee17f (Added pojo classes to data layer)

    public SubjectData(long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
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
