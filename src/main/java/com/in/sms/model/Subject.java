package com.in.sms.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"semester","teacher"})
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
