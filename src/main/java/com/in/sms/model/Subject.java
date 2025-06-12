package com.in.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;
    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false,unique = true)
    private String branch;

    @ManyToOne()
    private Semester semester;

    @ManyToMany(mappedBy = "subject")
    private List<Teacher> teacher;


}
