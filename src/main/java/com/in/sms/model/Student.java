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
public class Student {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String rollNo;

    @ManyToOne(fetch = FetchType.EAGER)
    private Semester semester;

    @Column(nullable = false,unique = true)
    private String branch;

    private String category;

    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    private Parents parents;

    @OneToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Document> document;
}
