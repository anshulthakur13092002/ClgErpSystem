package com.example.collegeerp.service.impl;

import com.example.collegeerp.model.Subject;
import com.example.collegeerp.repository.SubjectRepository;
import com.example.collegeerp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElse(null);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
    // Additional methods as needed
}
