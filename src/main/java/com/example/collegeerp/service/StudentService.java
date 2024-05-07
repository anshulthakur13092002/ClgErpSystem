package com.example.collegeerp.service;

import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Subject;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);
    Optional<Student> getStudentById(Long studentId);
    List<Student> getAllStudents();
//    public Student updateSubjectsAndGrades(Student updatedStudent, Long id);
      Student updateGrade(Student student, Long id);

    List<Student> getStudentBYCourse(String courseName);


    Student getStudentByEmail(String username) ;
//    public List<Student> updateStudentSubjects(Long studentId, List<Subject> updatedSubjects);
//public Student updateGrade(Optional<Student> student, Long id);
}

