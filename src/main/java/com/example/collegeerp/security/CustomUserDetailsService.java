package com.example.collegeerp.security;

import com.example.collegeerp.model.Student;
import com.example.collegeerp.model.Teacher;
import com.example.collegeerp.repository.StudentRepository;
import com.example.collegeerp.repository.TeacherRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CustomUserDetailsService(StudentRepository repository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Student credentials=studentRepository.findByemail(username);
        if(credentials==null){
            Teacher credentials1 =teacherRepository.findAllByemail(username);
            return  credentials1;
        }
        return credentials;

    }


}















//
//        return new CustumUserDetails(credentials.getEmail(),credentials.getPassword(),getAuthorities(credentials));
////        User1 credentials = userRepository.findByEmail(username).orElseThrow(RuntimeException::new);
// private Set<SimpleGrantedAuthority>getAuthorities(User credentials){
//        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
//        credentials.getRole().forEach(role -> {
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
//        });
//        return authorities;
// }
