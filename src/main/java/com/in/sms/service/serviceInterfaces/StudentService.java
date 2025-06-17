package com.in.sms.service.serviceInterfaces;

import com.in.sms.dto.student.StudentRequestDto;
import com.in.sms.dto.student.StudentResponseDto;
import com.in.sms.dto.student.StudentSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    StudentResponseDto saveStudent(StudentRequestDto student);
    StudentResponseDto getStudentById(Long id);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto updateStudent(Long id, StudentRequestDto updatedStudent);
    void deleteStudent(Long id);
    Page<StudentResponseDto> searchStudent(StudentSearchDto studentSearchDto, Pageable pageable);
}
