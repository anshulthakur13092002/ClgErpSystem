package com.example.collegeerp.service;

import com.example.collegeerp.model.JwtRequest;
import com.example.collegeerp.model.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface JwtService {
    ResponseEntity<JwtResponse> login(JwtRequest request);
}
