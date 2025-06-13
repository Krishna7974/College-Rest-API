package com.in.sms.dto.teacher;

import lombok.Data;

import java.util.List;

@Data
public class TeacherResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<String> subject;
}
