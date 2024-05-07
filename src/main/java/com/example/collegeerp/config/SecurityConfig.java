package com.example.collegeerp.config;

import com.example.collegeerp.security.CustomUserDetailsService;
import com.example.collegeerp.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, CustomUserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/**","/login", "/view/**", "/loginController", "/static/**", "/doLogin", "/home", "/allStudents", "/addStudent", "/register", "/addStudent/****", "/delete/**", "/getStudent/***", "/updateStudent/**","/student-loginController","/do-student-loginController","/student-home","/getStudentDetails/**","/api/user1/***","/api/users","/api/attendance","auth/login/**"
                        ,"/logon1","/admin-home","/allFaculty","/allStudent","api/faculties","/btechFaculty",
                        "/bfarmFaculty","/bscFaculty","/bcaFaculty","/register-faculty","/addFaculty","/login","/teachers/updateMarks/**","/students/updateMarks/**","/students","/students/updateSubjects/**","/login1","/doLogin","/students","/teachers/byCourse/**","/bfarmStudent","/btechStudent","/bcaStudent","/bscStudent","/courses")
                .permitAll()
                .antMatchers("/api/attendance","/api/students")
                .hasRole("TEACHER")

                .antMatchers("/teachers","/teachers","/studentByID/**")
                .hasRole("ADMIN")
                .antMatchers("/studentByID/**")
                .hasRole("STUDENT")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return  httpSecurity.build();
    }
    @Bean
    public DaoAuthenticationProvider daDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}




