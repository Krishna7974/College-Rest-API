package com.in.sms.dto.student;

import com.in.sms.model.Document;
import com.in.sms.model.Parents;
import com.in.sms.model.Semester;
import lombok.Data;

import java.util.List;

@Data
public class StudentRequestDto {

    private String name;
    private String email;
    private String password;
    private String rollNo;
    private Semester semester;
    private String branch;
    private String category;
    private String address;
    private Parents parents;
    private List<Document> document;

}
