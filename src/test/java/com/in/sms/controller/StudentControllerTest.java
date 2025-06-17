package com.in.sms.controller;

import com.in.sms.model.Student;
import com.in.sms.repository.StudentRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentControllerTest {

    @Autowired
    private StudentRepository studentRepository;

    @ParameterizedTest
    @CsvSource({
            "Ajay Singh",
            "Suresh Kuman",
            "krishna Patidar",
            "krishna",
            "lalit",
            "shivam"
    })
    void findStudentByNameTest(String name) {
        List<Student> list=studentRepository.getStudentByName(name);
        assertTrue(!list.isEmpty(),"student found");
    }

}