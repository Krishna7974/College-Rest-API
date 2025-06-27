package com.in.sms.service;

import com.in.sms.model.Student;
import com.in.sms.model.Teacher;
import com.in.sms.model.User;
import com.in.sms.repository.StudentRepository;
import com.in.sms.repository.TeacherRepository;
import com.in.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

//    @Autowired
//    private StudentRepository studentRepository;
//
//    @Autowired
//    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

//        Optional<Student> student = studentRepository.findByEmail(username);
//        if (student.isPresent()) {
//            Student s = student.get();
//            return new org.springframework.security.core.userdetails.User(
//                    s.getEmail(),
//                    s.getPassword(),
//                    s.getRoles().stream()
//                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                            .toList()
//            );
//        }
//
//        Optional<Teacher> teacher = teacherRepository.findByEmail(username);
//        if (teacher.isPresent()) {
//            Teacher t=teacher.get();
//            return new org.springframework.security.core.userdetails.User(
//                    t.getEmail(),
//                    t.getPassword(),
//                    t.getRoles().stream()
//                            .map(role->new SimpleGrantedAuthority("ROLE_"+role))
//                            .toList()
//            );
//        }

        Optional<User> user=userRepository.findByEmail(email);
        if (user.isPresent()) {
            User u=user.get();
            return new org.springframework.security.core.userdetails.User(
                    u.getEmail(),u.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole()))
            );
        }

        throw new RuntimeException("User not found with email: " + email);
    }
}
