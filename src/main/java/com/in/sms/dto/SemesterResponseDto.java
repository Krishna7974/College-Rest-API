package com.in.sms.dto;

import lombok.Data;

import java.util.List;

@Data
public class SemesterResponseDto {

    private Long id;
    private String name;
    private Integer numberOfStudent;
    private List<String> listOfSubjects;
}
