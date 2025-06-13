package com.in.sms.dto.teacher;

import lombok.Data;

import java.util.List;

@Data
public class TeacherRequestDto {
    private String name;
    private String email;
    private String password;
    private List<String> subject;
}
