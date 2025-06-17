package com.in.sms.service.serviceInterfaces;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.model.Semester;

import java.util.List;

public interface SemesterService {

    SemesterResponseDto saveSemester(Semester semester);
    SemesterResponseDto getSemesterById(Long id);
    SemesterResponseDto getSemesterByName(String classRoomName);
    List<SemesterResponseDto> getAllSemester();
    SemesterResponseDto updateSemester(Long id, Semester updatedSemester);
    void deleteSemester(Long id);

}
