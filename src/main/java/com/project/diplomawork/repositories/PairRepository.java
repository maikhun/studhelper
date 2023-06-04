package com.project.diplomawork.repositories;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.Pair;
import com.project.diplomawork.models.Schedule;
import com.project.diplomawork.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {
    List<Pair> findPairsByGroup(Group group);

    List<Pair> findPairsByWeekType(String name);

    List<Pair> findPairsByGroupAndWeekType(Group group, String type);

    List<Pair> findPairsByTeacher(Teacher teacher);

    List<Pair> findPairsByDayAndSchedule(String day, Schedule schedule);
}
