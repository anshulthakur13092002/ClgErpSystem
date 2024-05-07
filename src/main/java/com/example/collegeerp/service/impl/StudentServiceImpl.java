package com.example.collegeerp.service.impl;

import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Subject;
import com.example.collegeerp.repository.StudentRepository;
import com.example.collegeerp.repository.SubjectRepository;
import com.example.collegeerp.service.StudentService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubjectRepository subjectRepository;


    public StudentServiceImpl(StudentRepository studentRepository, PasswordEncoder passwordEncoder, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.subjectRepository = subjectRepository;
    }

    //    @Override
//    public Student saveStudent(Student student) {
//        student.setFirstName(student.getFirstName());
//        student.setLastName(student.getFirstName());
//        student.setAddress(student.getAddress());
//        student.setPassword(passwordEncoder.encode(student.getPassword()));
//        student.setRole(student.getRole());
//        student.setSubject(student.getSubject().stream().map(subject ->{
//            Subject subject1=new Subject();
//            subject1.setSubjectName(subject.getSubjectName());
//            subject1.setGrade(subject.getGrade());
//            subjectRepository.save(subject1);
//            return subject1;
//        }).collect(Collectors.toList()));
//
//        return studentRepository.save(student);
//    }
    @Override
    public Student saveStudent(Student student) {
        // Encode password
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setRole("STUDENT");

        // Save student and get the saved instance
        Student savedStudent = studentRepository.save(student);

        // Associate subjects with the saved student
        if (student.getSubject() != null) {
            System.out.println("888888888888888888888888888" + student.getSubject());
            // Create new Subject instances based on the subjects associated with the input Student
            List<Subject> newSubjects = student.getSubject().stream().map(subject -> {
                Subject newSubject = new Subject();
                newSubject.setSubjectName(subject.getSubjectName());
                newSubject.setGrade(subject.getGrade());
                // Save the new Subject to the database
                subjectRepository.save(newSubject);

                return newSubject;
            }).collect(Collectors.toList());

            // Set the new subjects on the saved student
            savedStudent.setSubject(newSubjects);
        }

        // Save the updated student entity with associated subjects
        return studentRepository.save(savedStudent);
    }
   @Override
    public Student getStudentByEmail(String username) {
      return studentRepository.findByemail(username);
    }

    @Override
    public Optional<Student> getStudentById(Long studentId) {
        Optional<Student> student = null;
        student = (studentRepository.findById(studentId));


        return student;
    }
    @Override
    public List<Student> getStudentBYCourse(String courseName){
        return studentRepository.findByCourseName(courseName);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    @Override
    public Student updateGrade(Student student, Long id) {
        Optional<Student> existingStudentOptional;
        existingStudentOptional= studentRepository.findById(id);
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            System.out.println("`````````````````````````````````````"+student.getSubject());
            existingStudentOptional.get().setSubject(student.getSubject().stream().map(subject -> {
                Subject existingSubject = existingStudent.getSubject().stream()
                        .filter(s -> s.getSubjectId().equals(subject.getSubjectId()))
                        .findFirst().orElseThrow(() -> new RuntimeException("Subject not found for student with ID: " + id));

                existingSubject.setSubjectName(subject.getSubjectName());
                existingSubject.setGrade(subject.getGrade());
                subjectRepository.save(existingSubject);
                return existingSubject;
            }).collect(Collectors.toList()));

            return studentRepository.save(existingStudent);
        }
        throw new RuntimeException("Student not found with ID: " + id);
    }



}


//    @Override
//    public List<Student> updateStudentSubjects(Long studentId, List<Subject> updatedSubjects) {
//        Optional<Student> existingStudentOptional = studentRepository.findById(studentId);
//
//        if (existingStudentOptional.isPresent()) {
//            Student existingStudent = existingStudentOptional.get();
//
//            List<Subject> currentSubjects = existingStudent.getSubject();
//
//            // Iterate over updated subjects
//            for (Subject updatedSubject : updatedSubjects) {
//                Long subjectId = updatedSubject.getSubjectId();
//
//                // Find the corresponding subject in the current list
//                Optional<Subject> currentSubjectOptional = currentSubjects.stream()
//                        .filter(subject -> subject.getSubjectId().equals(subjectId))
//                        .findFirst();
//
//                if (currentSubjectOptional.isPresent()) {
//                    // Update the grade of the current subject
//                    Subject currentSubject = currentSubjectOptional.get();
//                    currentSubject.setGrade(updatedSubject.getGrade());
//                    subjectRepository.save(currentSubject);
//                } else {
//                    // Handle case where the subject is not found
//                    // You can throw an exception, log a message, etc.
//                }
//            }
//
//            // Save the updated student to the repository
//            studentRepository.save(existingStudent);
//
//            // Return the updated list of students (optional)
//            return getAllStudents();
//        }
//        return null;
//    }}
    // Additional methods as needed
//    @Override
//    public Student updateSubjectsAndGrades(Student updatedStudent, Long id) {
//        Optional<Student> existingStudentOptional = studentRepository.findById(id);
//        if (existingStudentOptional.isPresent()) {
//            Student existingStudent = existingStudentOptional.get();
//            System.out.println("999999999999999999999999999999"+existingStudent);
//            System.out.println("+++++++++++++++++++++++++++++++"+existingStudent.getSubject());
//            existingStudent.setSubject(existingStudent.getSubject().stream().map(subject -> {
//
//                Subject existingSubject = existingStudent.getSubject().stream()
//
//                        .filter(s -> s.getSubjectId().equals(subject.getSubjectId()))
//                        .findFirst().orElseThrow(() -> new RuntimeException("Subject not found for student with ID: " + id));
//
//                existingSubject.setSubjectName(subject.getSubjectName());
//                existingSubject.setGrade(subject.getGrade());
//                return existingSubject;
//            }).collect(Collectors.toList()));
//
//            return studentRepository.save(existingStudent);
//        }
//        throw new RuntimeException("Student not found with ID: " + id);
//    }
//
//
//}



