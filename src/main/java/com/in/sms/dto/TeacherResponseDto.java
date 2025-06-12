package com.in.sms.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherResponseDto {
    private String name;
    private String email;
    private List<String> subject;
}
