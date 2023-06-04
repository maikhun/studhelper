package com.project.diplomawork.services;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.Pair;
import com.project.diplomawork.models.Teacher;
import com.project.diplomawork.repositories.PairRepository;
import com.project.diplomawork.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PairRepository pairRepository;
    /**
     * Список преподавателей определенной группы
     * @param group Студенческая группа
     * @return  Список преподавателей
     * */
    public List<Teacher> findTeachersByGroup(Group group) {
        List<Pair> groupPairs = pairRepository.findPairsByGroup(group); // Получение всех пар определенной студенческой группы группы
        List<Teacher> groupTeachers = new ArrayList<>();
        for (int i = 0; i < groupPairs.size(); i++) {
            Teacher teacher = groupPairs.get(i).getTeacher();
            if (groupTeachers.contains(teacher)) continue;
            groupTeachers.add(groupPairs.get(i).getTeacher());
        }
        return groupTeachers;
    }

    public List<Pair> getPairsByTeacher(Teacher teacher) {
        return pairRepository.findPairsByTeacher(teacher);
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

}
