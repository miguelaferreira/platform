package com.me.miguelferreira.platform.data.repositories;

import com.me.miguelferreira.platform.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
}
