package com.project.diplomawork.services;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.Pair;
import com.project.diplomawork.models.Schedule;
import com.project.diplomawork.repositories.PairRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PairService {

    private final PairRepository pairRepository;

    public List<Pair> getPairsByGroup(Group group) {
        return pairRepository.findPairsByGroup(group);
    }

    public List<Pair> getPairsByWeekType(String type) {
        return pairRepository.findPairsByWeekType(type);
    }

    public List<Pair> getPairsByGroupAndWeek(Group group, String type) {
        return pairRepository.findPairsByGroupAndWeekType(group, type);
    }

    public List<Pair> getPairsByDay(String day, Schedule schedule) {
        return pairRepository.findPairsByDayAndSchedule(day, schedule);
    }

    public Optional<Pair> getPairById(Long id) {
        return pairRepository.findById(id);
    }
}
