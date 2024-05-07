package com.example.collegeerp.service.impl;


import com.example.collegeerp.controller.AuthController;
import com.example.collegeerp.model.JwtRequest;
import com.example.collegeerp.model.JwtResponse;
import com.example.collegeerp.security.JwtHelper;
import com.example.collegeerp.service.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtServiceImpl implements JwtService {
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtHelper helper;



    public JwtServiceImpl(UserDetailsService userDetailsService, AuthenticationManager authenticationManager, JwtHelper helper) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.helper = helper;
    }

    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @Override
    public ResponseEntity<JwtResponse> login(JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
