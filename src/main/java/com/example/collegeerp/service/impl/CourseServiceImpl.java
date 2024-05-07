package com.example.collegeerp.service.impl;

import com.example.collegeerp.model.Course;
import com.example.collegeerp.repository.CourseRepository;
import com.example.collegeerp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course saveCourse(Course course) {


        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    // Additional methods as needed


}

