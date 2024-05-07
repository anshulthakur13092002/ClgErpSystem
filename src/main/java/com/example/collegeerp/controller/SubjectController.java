package com.example.collegeerp.controller;

import com.example.collegeerp.model.Subject;
import com.example.collegeerp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.saveSubject(subject);
    }

    @GetMapping("/{subjectId}")
    public Subject getSubjectById(@PathVariable Long subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    @GetMapping
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }
    // Additional endpoints as needed
}

