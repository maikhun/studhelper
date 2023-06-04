package com.project.diplomawork.services;

import com.project.diplomawork.models.Pair;
import com.project.diplomawork.models.User;
import com.project.diplomawork.models.VirtualQueue;
import com.project.diplomawork.repositories.VirtualQueueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

@Service
@Slf4j
@RequiredArgsConstructor
public class VirtualQueueService {

    private final VirtualQueueRepository virtualQueueRepository;
    private final UserService userService;

    /**
     * Добавление пользователя в очередь
     * @param user - Пользователь
     * @param pair - Студенческое занятие
     * */
    public boolean addUserInQueue(User user, Pair pair) {
        var virtualQueue = virtualQueueRepository.findById(pair.getId()).orElse(null);

        if (virtualQueue != null) {
            Queue<User> queueStudents = new LinkedList<>(virtualQueue.getUsers());
            if (queueStudents.contains(user)) {
                return false;
            }
            queueStudents.add(user);


            virtualQueue.setPair(pair);
            virtualQueue.setUsers(queueStudents.stream().toList());
            virtualQueue.setId(pair.getId());
            virtualQueueRepository.save(virtualQueue);
            return true;
        }
        virtualQueue = createQueue();
        Queue<User> queueStudents = new LinkedList<>();
        queueStudents.add(user);

        virtualQueue.setUsers(queueStudents.stream().toList());
        virtualQueue.setPair(pair);
        virtualQueue.setId(pair.getId());
        virtualQueueRepository.save(virtualQueue);
        return true;
    }

    private VirtualQueue createQueue() {
        VirtualQueue virtualQueue = new VirtualQueue();
        return virtualQueue;
    }

    public VirtualQueue findByPair(Pair pair) {
        return virtualQueueRepository.findByPair(pair);
    }

    public Optional<VirtualQueue> findById(Long id) {
        return virtualQueueRepository.findById(id);
    }



}
