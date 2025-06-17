package com.in.sms.dto.student;

import lombok.Data;

@Data
public class StudentSearchDto {

    private String stdName;
    private String sem;
    private String branch;
    private String category;
    private String address;
    private String rollNo;
    private String email;
    private String phoneNo;
}
