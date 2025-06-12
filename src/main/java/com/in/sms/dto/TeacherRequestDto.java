package com.in.sms.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherRequestDto {
    private String name;
    private String email;
    private List<String> subject;
}
