package com.project.diplomawork.repositories;

import com.project.diplomawork.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findByName(String name);

}
