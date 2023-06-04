package com.project.diplomawork.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "type")
    private String type;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Pair> pairs = new ArrayList<>();

    @OneToOne
    private Group group;
}
