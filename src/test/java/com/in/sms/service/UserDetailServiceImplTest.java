package com.in.sms.service;

import com.in.sms.model.Student;
import com.in.sms.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserDetailServiceImplTest {

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername() {
        when(studentRepository.findByEmail(anyString())).thenReturn(Optional.ofNullable(Student.builder().email("lalit@gmail.com").password("lalit").roles(new ArrayList<>()).build()));
        UserDetails student=userDetailService.loadUserByUsername("lalit@gmail.com");
        Assertions.assertNotNull(student);
    }
}