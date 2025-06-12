package com.in.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Parents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String fatherName;
    @Column(nullable = false)
    private String motherName;

    private Integer siblings;

    @JsonIgnore
    @OneToMany(mappedBy = "parents",cascade = CascadeType.ALL)
    private List<Student> students;

}
