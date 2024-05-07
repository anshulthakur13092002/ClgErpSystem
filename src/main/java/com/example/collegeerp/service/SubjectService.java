package com.example.collegeerp.service;

import com.example.collegeerp.model.Subject;

import java.util.List;

public interface SubjectService {
    Subject saveSubject(Subject subject);
    Subject getSubjectById(Long subjectId);
    List<Subject> getAllSubjects();
    // Additional methods as needed
}
