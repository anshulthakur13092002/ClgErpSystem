package com.example.collegeerp.repository;

import com.example.collegeerp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findAllByemail(String username);

//    Teacher findAllBycourse_name(String courseName);

    @Query("SELECT t FROM Teacher t WHERE t.course.courseName = :courseName")
    List<Teacher> findByCourseName(@Param("courseName") String courseName);





}
