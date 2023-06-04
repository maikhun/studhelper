package com.project.diplomawork.services;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.Pair;
import com.project.diplomawork.models.Schedule;
import com.project.diplomawork.repositories.PairRepository;
import com.project.diplomawork.repositories.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final PairService pairService;
    /**
     * Создание расписания
     * @param group - Номер студенческой группы
     * @return Расписание
     * */
    private Schedule createSchedule(Group group) {
        String type = "Числитель";
        Schedule schedule = scheduleRepository.findByGroup(group);
        if (schedule != null)
            return schedule;
        Schedule newSchedule = new Schedule();
        newSchedule.setGroup(group);
        newSchedule.setType(type);
        scheduleRepository.save(newSchedule);
        return newSchedule;
    }

    /**
     * Создание расписания
     * @param group - Номер студенческой группы
     * */
    public void generateSchedule(Group group) {
        var schedule = createSchedule(group);
        schedule.setPairs(pairService.getPairsByGroupAndWeek(group, schedule.getType()));
        pairService.getPairsByGroupAndWeek(group, schedule.getType());

        scheduleRepository.save(schedule);
    }

    public Schedule findByGroup(Group group) {
        return scheduleRepository.findByGroup(group);
    }
}
