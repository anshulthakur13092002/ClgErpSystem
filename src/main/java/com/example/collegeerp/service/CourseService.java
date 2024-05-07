package com.example.collegeerp.service;

import com.example.collegeerp.model.Course;

import java.util.List;

public interface CourseService {
    Course saveCourse(Course course);
    Course getCourseById(Long courseId);
    List<Course> getAllCourses();
    // Additional methods as needed
}

