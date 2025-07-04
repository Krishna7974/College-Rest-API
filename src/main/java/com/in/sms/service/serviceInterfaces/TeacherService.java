package com.in.sms.service.serviceInterfaces;

import com.in.sms.dto.teacher.TeacherRequestDto;
import com.in.sms.dto.teacher.TeacherResponseDto;
import com.in.sms.dto.teacher.TeacherSearchDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {
    TeacherResponseDto saveTeacher(TeacherRequestDto teacher);
    TeacherResponseDto getTeacherById(Long id);
    List<TeacherResponseDto> getAllTeachers();
    TeacherResponseDto updateTeacher(Long id, TeacherRequestDto updatedTeacher);
    void deleteTeacher(Long id);
    List<TeacherResponseDto> searchTeacher(TeacherSearchDto dto, Pageable pageable);
}
