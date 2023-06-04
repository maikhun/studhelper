package com.project.diplomawork.repositories;

import com.project.diplomawork.models.Pair;
import com.project.diplomawork.models.VirtualQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VirtualQueueRepository extends JpaRepository<VirtualQueue, Long> {
    VirtualQueue findByPair(Pair pair);
}
