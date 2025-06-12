package com.in.sms.service;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.model.Semester;
import com.in.sms.model.Student;
import com.in.sms.model.Subject;
import com.in.sms.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface SemesterService {

    SemesterResponseDto saveSemester(Semester semester);
    SemesterResponseDto getSemesterById(Long id);
    SemesterResponseDto getSemesterByName(String classRoomName);
    List<SemesterResponseDto> getAllSemester();
    SemesterResponseDto updateSemester(Long id, Semester updatedSemester);
    void deleteSemester(Long id);

}
