package com.project.diplomawork.repositories;

import com.project.diplomawork.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
