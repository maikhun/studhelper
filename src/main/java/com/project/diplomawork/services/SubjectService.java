package com.project.diplomawork.services;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.Pair;
import com.project.diplomawork.models.Subject;
import com.project.diplomawork.repositories.PairRepository;
import com.project.diplomawork.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final PairRepository pairRepository;

    public List<Subject> findSubjectsByGroup(Group group) {
        List<Pair> groupPairs = pairRepository.findPairsByGroup(group);
        Set<Subject> groupSubjects = new HashSet<>();
        for (int i = 0; i < groupPairs.size(); i++) {
            groupSubjects.add(groupPairs.get(i).getSubject());
        }
        return groupSubjects.stream().toList();
    }

    public Optional<Subject> findById(Long id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> findSubjectsByPair(List<Pair> pairs){
        Set<Subject> uniqueSubjects = new LinkedHashSet<>();
        for (int i = 0; i < pairs.size(); i++) {
            uniqueSubjects.add(pairs.get(i).getSubject());
        }
        List<Subject> subjects = new ArrayList<>(uniqueSubjects);
        return subjects;
    }

    public Subject findByName(String name) {
        return subjectRepository.findByName(name);
    }

}
