package com.example.collegeerp.controller;

import com.example.collegeerp.model.Course;
import com.example.collegeerp.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public Course addCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
    // Additional endpoints as needed
}
