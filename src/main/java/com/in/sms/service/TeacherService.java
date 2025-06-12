package com.in.sms.service;

import com.in.sms.dto.TeacherRequestDto;
import com.in.sms.dto.TeacherResponseDto;
import com.in.sms.dto.TeacherSearchDto;
import com.in.sms.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {
    TeacherResponseDto saveTeacher(TeacherRequestDto teacher);
    TeacherResponseDto getTeacherById(Long id);
    List<TeacherResponseDto> getAllTeachers();
    TeacherResponseDto updateTeacher(Long id, Teacher updatedTeacher);
    void deleteTeacher(Long id);
    Page<Teacher> searchTeacher(TeacherSearchDto dto, Pageable pageable);
}
