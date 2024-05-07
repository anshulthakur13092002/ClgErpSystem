package com.example.collegeerp.controller;

import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Subject;
import com.example.collegeerp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @GetMapping("studentByID/{studentId}")
    public Optional<Student> getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }


    @PutMapping("updateMarks/{id}")
    public Student updateGrade(@RequestBody Student student, @PathVariable Long id) {
        return  studentService.updateGrade(student,id);}


     @GetMapping("/studentByCourse/{courseName}")
    public List<Student> getStudentBYCourse(@PathVariable String courseName){
        return  studentService.getStudentBYCourse(courseName);
    }
     @GetMapping("/studentByEmail/{username}")
    public Student getStudentByEmail(@PathVariable String username) {
        return  studentService.getStudentByEmail(username);

    }
}

