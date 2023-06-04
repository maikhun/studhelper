package com.project.diplomawork.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * Сущность "Teacher" - предназначена для хранения информации об преподавателях
 *
 */

@Entity
@Table(name = "teacher")
@Data
public class Teacher {

    // Id - Первичный ключ, идентификатор преподавателя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Long id;

    // Firstname - Имя преподавателя
    @Column(name = "firstname", nullable = false, length = 50)
    private String firstname;

    // Secondname - Фамилия преподавателя
    @Column(name = "secondname", nullable = false,length = 100)
    private String secondname;

    // Patronymic - Отчество преподавателя
    @Column(name = "patronymic", nullable = false, length = 50)
    private String patronymic;

    // Degree - Научная степень преподавателя
    @Column(name = "post", nullable = false)
    private String post;

    // Email - Email преподавателя
    @Column(name = "email", nullable = true)
    private String email;

    // VkUrl - VK преподавателя
    @Column(name = "vk", nullable = true)
    private String vkUrl;

    public String getFirstSymbol(String name) {
        String dotName = name.toUpperCase().substring(0,1);
        return dotName + ".";
    }

}
