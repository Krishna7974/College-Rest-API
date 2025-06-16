package com.in.sms.dto.student;

import com.in.sms.model.Document;
import com.in.sms.model.Parents;
import com.in.sms.model.Semester;
import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDto {
    private Long id;
    private String name;
    private String email;
    private String rollNo;
    private String phoneNo;
    private Semester semester;
    private String branch;
    private String category;
    private String address;
    private Parents parents;
    private List<Document> document;
}
