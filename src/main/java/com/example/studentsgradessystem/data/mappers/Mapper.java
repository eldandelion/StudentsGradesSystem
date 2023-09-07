package com.example.studentsgradessystem.data.mappers;

import com.example.studentsgradessystem.data.pojo.GradeData;
import com.example.studentsgradessystem.data.pojo.StudentData;
import com.example.studentsgradessystem.data.pojo.SubjectData;
import com.example.studentsgradessystem.data.pojo.TeacherData;
import com.example.studentsgradessystem.domain.pojo.Grade;
import com.example.studentsgradessystem.domain.pojo.Student;
import com.example.studentsgradessystem.domain.pojo.Subject;
import com.example.studentsgradessystem.domain.pojo.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Mapper {


    public static Subject subDataToEntity(SubjectData data) {
        return new Subject(data.getSubjectId(), data.getTeacherId(), data.getSubjectName());
    }

    public static SubjectData entityToSubData(Subject entity) {
        return new SubjectData(entity.getSubjectId(), entity.getTeacherId(), entity.getSubjectName());
    }

    public static List<Subject> subDataToEntityList(List<SubjectData> list) {
        List<Subject> subjectList = new ArrayList<>();
        for (SubjectData s : list) subjectList.add(subDataToEntity(s));
        return subjectList;
    }

    public static List<SubjectData> subEntityToDataList(List<Subject> list) {
        List<SubjectData> subjectList = new ArrayList<>();
        for (Subject s : list) subjectList.add(entityToSubData(s));
        return subjectList;
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

    public static StudentData entityToStudentData(Student entity) {
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

    public static Teacher teacherDataToEntity(TeacherData teacherData) {
        List<Student> studentList = listStuToEntity(teacherData.getStudents());
        List<Subject> subjectList = subDataToEntityList(teacherData.getSubjects());

        return new Teacher(teacherData.getTeacherId(), teacherData.getEmail(), teacherData.getName(), teacherData.getPassword(), subjectList, studentList);
    }

    public static TeacherData teacherEntityToData(Teacher teacher) {
        List<StudentData> studentList = listEntityToStudent(teacher.getStudents());
        List<SubjectData> subjectList = subEntityToDataList(teacher.getSubjects());

        return new TeacherData(teacher.getTeacherId(), teacher.getEmail(), teacher.getName(), teacher.getPassword(), subjectList, studentList);
    }
    public static List<Teacher> teacherDataToEntityList(List<TeacherData> list) {
        List<Teacher> teacherList = new ArrayList<>();
        for (TeacherData t : list) teacherList.add(teacherDataToEntity(t));
        return teacherList;
    }

    public static List<TeacherData> teacherEntityToDataList(List<Teacher> list) {
        List<TeacherData> teacherList = new ArrayList<>();
        for (Teacher t : list) teacherList.add(teacherEntityToData(t));
        return teacherList;
    }


}
