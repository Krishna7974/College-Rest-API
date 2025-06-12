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
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
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
