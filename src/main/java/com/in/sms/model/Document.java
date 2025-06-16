package com.in.sms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "student")
public class Document {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String fileType;

    @Lob
    @Column(columnDefinition = "LONGBLOB", name = "file_data")
    private byte[] doc;

    @JsonIgnore
    @ManyToOne()
    private Student student;
}
