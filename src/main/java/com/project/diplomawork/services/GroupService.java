package com.project.diplomawork.services;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.User;
import com.project.diplomawork.repositories.GroupRepository;
import com.project.diplomawork.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final ScheduleService scheduleService;
    private final UserRepository userRepository;

    /**
     * Добавление пользователя в студенческую группу
     * @param user - Пользователь
     * */
    private Group createGroup(User user) {
        Group group = groupRepository.findByGroupNumber(user.getGroupNumber());
        if (group != null)
            return group;
        Group newGroup = new Group();
        newGroup.setGroupNumber(user.getGroupNumber());
        groupRepository.save(newGroup);
        return newGroup;
    }

    /**
     * Добавление пользователя в студенческую группу
     * @param user - Пользователь
     * */
    public void addUserInGroup(User user) {
        var group = createGroup(user);
        group.addUserInGroup(user);

        user.setGroup(group);

        userRepository.save(user);
        groupRepository.save(group);
    }

    public Group getGroupByNumber(String groupNumber){
        return groupRepository.findByGroupNumber(groupNumber);
    }

}
