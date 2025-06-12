package com.in.sms.service;

import com.in.sms.dto.StudentSearchDto;
import com.in.sms.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    Student updateStudent(Long id, Student updatedStudent);
    void deleteStudent(Long id);
    List<Student> getStudentsByClassRoomId(Long classRoomId);
    Page<Student> searchStudent(StudentSearchDto studentSearchDto, Pageable pageable);
}
