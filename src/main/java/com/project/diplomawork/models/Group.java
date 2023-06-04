package com.project.diplomawork.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Entity
@Table( name = "user_group")
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    @Column(name = "group_number", unique = true)
    private String groupNumber;

    @OneToMany(mappedBy = "group",fetch = FetchType.EAGER)
    private List<User> users = new LinkedList<>();

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<Pair> pair;

    @OneToOne(mappedBy = "group", fetch = FetchType.EAGER)
    private Schedule schedule;

    /**
     * Добавление пользователя в группу
     * @param user - Пользователь
     */
    public void addUserInGroup(User user) {
        user.setGroup(this);
        users.add(user);
    }

}
