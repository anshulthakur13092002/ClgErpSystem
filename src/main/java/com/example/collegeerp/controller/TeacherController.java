package com.example.collegeerp.controller;

import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Teacher;
import com.example.collegeerp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @GetMapping("/{teacherId}")
    public Teacher getTeacherById(@PathVariable Long teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();}

    @GetMapping("/byCourse/{courseName}")
    public List<Teacher> getTeacherByCourse(@PathVariable  String courseName ){
        return teacherService.getTeacherByCourse(courseName);

    }
    @GetMapping("/byEmail/{email}")
    public Teacher getTeacherByEmail(@PathVariable String email){
        return teacherService.getTeacherByEmail(email);
    }
}
