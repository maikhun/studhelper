package com.project.diplomawork.services;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.User;
import com.project.diplomawork.models.enums.Role;
import com.project.diplomawork.repositories.GroupRepository;
import com.project.diplomawork.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GroupRepository groupRepository;

    public boolean createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            return false;

        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        userRepository.save(user);
        return true;
    }

    // Изменение информации о пользователе
    public boolean updateUser(User user) {
        var userFromDb = userRepository.findById(user.getId()).get();

        if (!user.getEmail().isBlank()) {
            userFromDb.setEmail(user.getEmail());
        }
        if (!user.getPassword().isBlank()) {
            userFromDb.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        if (!user.getTelegram().isBlank()) {
            userFromDb.setTelegram(user.getTelegram());
        }

        if (!user.getGithubUrl().isBlank()) {
            userFromDb.setGithubUrl(user.getGithubUrl());
        }

        if (!user.getVkUrl().isBlank()) {
            userFromDb.setVkUrl(user.getVkUrl());
        }
        userRepository.save(userFromDb);
        return true;
    }

    public void saveUser(User user) {

        userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User findUserByEmail(String username) {
        return userRepository.findByEmail(username);
    }

}
