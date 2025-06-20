package com.in.sms.controller;

import com.in.sms.dto.SemesterResponseDto;
import com.in.sms.model.Semester;
import com.in.sms.service.serviceInterfaces.SemesterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SemesterControllerTest {

    @Mock
    private SemesterService semesterService;

    @InjectMocks

    private SemesterController semesterController;

    @Test
    void saveSemesterName() {
        Semester s=new Semester("8");
        SemesterResponseDto expectedResponse=new SemesterResponseDto();
        expectedResponse.setName(s.getName());
        when(semesterService.saveSemester(s)).thenReturn(expectedResponse);
        ResponseEntity<SemesterResponseDto> srd=semesterController.saveSemester(s);

        assertEquals(s.getName(),srd.getBody().getName());
    }

    @Test
    void saveSemesterStatus() {
        Semester s=new Semester("8");
        SemesterResponseDto expectedResponse=new SemesterResponseDto();
        expectedResponse.setName(s.getName());
        when(semesterService.saveSemester(s)).thenReturn(expectedResponse);
        ResponseEntity<SemesterResponseDto> srd=semesterController.saveSemester(s);
        assertEquals(HttpStatus.OK,srd.getStatusCode());

    }
}