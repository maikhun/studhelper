package com.project.diplomawork.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Entity
@Table(name = "virtual_queue")
@Data
public class VirtualQueue {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.REFRESH)
    private Pair pair;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_queue", joinColumns = @JoinColumn (name = "queue_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users =  new LinkedList<>();
}
