package com.example.studentsgradessystem.data.mappers;

import com.example.studentsgradessystem.data.pojo.GradeData;
import com.example.studentsgradessystem.data.pojo.StudentData;
import com.example.studentsgradessystem.data.pojo.SubjectData;
import com.example.studentsgradessystem.domain.pojo.Grade;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Subject;

import java.util.ArrayList;
import java.util.List;

public class Mapper {


    public static Subject subDataToEntity(SubjectData data) {
        return new Subject(data.getSubjectId(), data.getTeacherId(),  data.getSubjectName());
    }

    public static SubjectData entityToSubData(Subject entity) {
        return new SubjectData(entity.getSubjectId(), entity.getTeacherId(), entity.getSubjectName());
    }

    public static Grade gradeDataToEntity(GradeData data) {
        return new Grade(subDataToEntity(data.getSubject()), data.getStudentNumber(), data.getStudentGrade());
    }

    public static GradeData entityToGradeData(Grade entity) {
        return new GradeData(entityToSubData(entity.getSubject()), entity.getStudentNumber(), entity.getStudentGrade());
    }

    public static Student stuDataToEntity(StudentData data) {
        List<Grade> gradeList = new ArrayList<>();

        for (GradeData grade : data.getGrades()) {
            gradeList.add(gradeDataToEntity(grade));
        }

        return new Student(data.getStudentId(), data.getStudentName(), gradeList);
    }

    public static StudentData entityToStudentData(Student entity){
        List<GradeData> gradeList = new ArrayList<>();

        for (Grade grade : entity.getGrades()) {
            gradeList.add(entityToGradeData(grade));
        }

        return new StudentData(entity.getStudentId(), entity.getStudentName(), gradeList);
    }

    public static List<Student> listStuToEntity(List<StudentData> list) {

        List<Student> studentList = new ArrayList<>();

        for (StudentData data : list) {
            studentList.add(stuDataToEntity(data));
        }
        return studentList;
    }

    public static List<StudentData> listEntityToStudent(List<Student> list) {

        List<StudentData> studentList = new ArrayList<>();

        for (Student data : list) {
            studentList.add(entityToStudentData(data));
        }
        return studentList;
    }


}
