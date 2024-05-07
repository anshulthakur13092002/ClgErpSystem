package com.example.collegeerp.repository;

import com.example.collegeerp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // Additional methods as needed
}

