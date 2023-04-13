package com.github.shazin.cqrs.ms.assignment.query.repo;

import com.github.shazin.cqrs.ms.assignment.query.entity.AssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface AssignmentRepository extends JpaRepository<AssignmentEntity, String> {

}
