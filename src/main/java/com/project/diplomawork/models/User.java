package com.project.diplomawork.models;

import com.project.diplomawork.models.enums.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 *
 * Сущность "User" - предназначена для хранения информации об авторизованных студентах
 *
 */

@Entity
@Table(name = "user_account")
@Data
public class User implements UserDetails {

    // Id - Первичный ключ, идентификатор пользователя
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // Firstname - Имя пользователя
    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    // Secondname - Фамилия пользователя
    @Column(name = "secondName", nullable = false, length = 100)
    private String secondName;

    // Email - Email пользователя
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    // GroupNumber - Номер группы пользователя
    @Column(name = "groupNumber", nullable = false, length = 10)
    private String groupNumber;

    // VkUrl - VK пользователя
    @Column(name = "vk", nullable = true)
    private String vkUrl;

    // GithubUrl - GitHub пользователя
    @Column(name = "github", nullable = true)
    private String githubUrl;

    @Column(name = "telegram", nullable = true)
    private String telegram;

    // Password - Пароль пользователя
    @Column(name="password", nullable = false,length = 100)
    private String password;

    // Active - Статус пользователя пользователя
    @Column(name = "active")
    private boolean active;

    // Roles - Роль пользователя
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    private Group group;

    // Переопределение методов Spring Security для профиля "User"

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
