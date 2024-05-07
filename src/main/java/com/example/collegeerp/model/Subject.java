package com.example.collegeerp.model;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "grade")
    private String grade;
    @ManyToMany
    private List<Student> students;

    public Subject() {

    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Subject(Long subjectId, String subjectName, String grade, List<Student> students) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.grade = grade;
        this.students = students;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectId=" + subjectId +
                ", subjectName='" + subjectName + '\'' +
                ", grade='" + grade + '\'' +
                ", students=" + students +
                '}';
    }
}
