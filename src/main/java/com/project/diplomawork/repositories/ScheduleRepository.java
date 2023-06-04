package com.project.diplomawork.repositories;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Schedule findByGroup(Group group);
}
