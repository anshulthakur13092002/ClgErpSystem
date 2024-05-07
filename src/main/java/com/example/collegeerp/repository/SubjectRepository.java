package com.example.collegeerp.repository;

import com.example.collegeerp.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // Additional methods as needed
}

