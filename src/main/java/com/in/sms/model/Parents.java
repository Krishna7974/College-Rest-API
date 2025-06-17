package com.in.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in.sms.customAnnotation.ValidPhoneNumber;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "students")
public class Parents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String fatherName;
    @Column(nullable = false)
    private String motherName;

    @ValidPhoneNumber
    private String phoneNo;

    private Integer siblings;

    @JsonIgnore
    @OneToMany(mappedBy = "parents",cascade = CascadeType.ALL)
    private List<Student> students;

}
