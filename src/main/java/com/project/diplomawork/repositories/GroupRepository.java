package com.project.diplomawork.repositories;

import com.project.diplomawork.models.Group;
import com.project.diplomawork.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByGroupNumber(String groupNumber);
    boolean existsByGroupNumber(String groupNumber);
}
