package com.example.collegeerp.repository;

import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


    Student findByemail(String username);
    @Query("SELECT s FROM Student s WHERE s.course.courseName= :courseName")
    List<Student> findByCourseName(@Param("courseName") String courseName);
}

