package com.project.diplomawork.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "pair")
@Data
public class Pair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "timeFrame")
    private String timeFrame;

    @Column(name = "audience")
    private String audience;

    @Column(name = "pair_type")
    private String type;

    @Column(name = "dayOfWeek")
    private String day;

    @Column(name = "weekType")
    private String weekType;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Schedule schedule;

    @OneToOne
    private Subject subject;

    @ManyToOne
    private Group group;

}
