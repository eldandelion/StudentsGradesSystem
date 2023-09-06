package com.example.studentsgradessystem.domain.pojo;

import java.util.List;

public class Teacher {

    private long teacherId;
    private String email;
    private String name;
    private String password;
    private List<Subject> subjects;
    private List<Student> students;

    public Teacher(long teacherId, String email, String name, String password, List<Subject> subjects, List<Student> students) {
        this.teacherId = teacherId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.subjects = subjects;
        this.students = students;
    }

    public Teacher() {
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
}
