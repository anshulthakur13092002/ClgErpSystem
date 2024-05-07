package com.example.collegeerp.service.impl;

import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Subject;
import com.example.collegeerp.model.Teacher;
import com.example.collegeerp.repository.StudentRepository;
import com.example.collegeerp.repository.SubjectRepository;
import com.example.collegeerp.repository.TeacherRepository;
import com.example.collegeerp.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {
private final PasswordEncoder passwordEncoder;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    @Autowired
    public TeacherServiceImpl(PasswordEncoder passwordEncoder, TeacherRepository teacherRepository, StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.passwordEncoder = passwordEncoder;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElse(null);
    }

    @Override
    public List<Teacher> getAllTeachers() {



        return teacherRepository.findAll();
    }
    @Override
    public List<Teacher> getTeacherByCourse(String courseName ){
        return  teacherRepository.findByCourseName(courseName);
    }

    @Override
    public Teacher getTeacherByEmail(String email){
        return  teacherRepository.findAllByemail(email);
    }

//@Override
//    public List<Student> updateGrade(Student student,Long Id) {
//    Optional<Student> existingUserOptional = studentRepository.findById(Id);
//    if (existingUserOptional.isPresent()) {
//        Student existingStudent = existingUserOptional.get();
//        existingStudent.setSubject(student.getSubject().stream().map(subject -> {
//            Subject subject1 = new Subject();
//            subject1.setSubjectName(subject1.getSubjectName());
//            subject1.setGrade(subject1.getGrade());
//            subjectRepository.save(subject1);
//            return subject1;
//        }).collect(Collectors.toList()));
//
//        return Collections.singletonList(studentRepository.save(existingStudent));
//    }
//        throw new RuntimeException("User not found with ID: " + Id);
//
//    }



//    @Override
//    public List<Student> updateGrade(Student student, Long id) {
//        Optional<Student> existingUserOptional = studentRepository.findById(id);
//
//        if (existingUserOptional.isPresent()) {
//            Student existingStudent = existingUserOptional.get();
//
//            // Update grades for subjects
//            List<Subject> updatedSubjects = student.getSubject().stream()
//                    .map(updatedSubject -> {
//                        Subject existingSubject = findSubjectById(existingStudent.getSubject(), updatedSubject.getSubjectId());
//
//                        // Assuming Subject has a method to update its properties
//                        if (existingSubject != null) {
//                            existingSubject.setGrade(updatedSubject.getGrade());
//                        } else {
//                            // If subject doesn't exist, consider throwing an exception or handling it appropriately
//                        }
//
//                        return existingSubject;
//                    })
//                    .collect(Collectors.toList());
//
//            // Set the updated list of subjects to the existing student
//            existingStudent.setSubject(updatedSubjects);
//
//            // Save the updated student to the repository
//            studentRepository.save(existingStudent);
//
//
//            return null;
//        }
//
//        throw new RuntimeException("User not found with ID: " + id);
//    }





}
