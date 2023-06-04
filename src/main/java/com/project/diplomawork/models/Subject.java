package com.project.diplomawork.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "subject")
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "department")
    private String department;

    @Column(name="subjectName")
    private String name;

    @Column(name = "subjectExamType")
    private String examType;
}
