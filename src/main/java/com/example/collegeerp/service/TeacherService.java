package com.example.collegeerp.service;

import com.example.collegeerp.model.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher saveTeacher(Teacher teacher);
    Teacher getTeacherById(Long teacherId);
    List<Teacher> getAllTeachers();
    List<Teacher> getTeacherByCourse(String courseName);
    Teacher getTeacherByEmail(String email);

}

