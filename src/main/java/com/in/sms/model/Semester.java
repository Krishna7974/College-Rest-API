package com.in.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.in.sms.customAnnotation.ValidSemester;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = {"student","subjects"})
@AllArgsConstructor
@NoArgsConstructor
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ValidSemester
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "semester")
    private List<Student> student;

    @JsonIgnore
    @OneToMany(mappedBy = "semester")
    private List<Subject> subjects;

    public Semester(String name) {
        this.name = name;
    }
}
