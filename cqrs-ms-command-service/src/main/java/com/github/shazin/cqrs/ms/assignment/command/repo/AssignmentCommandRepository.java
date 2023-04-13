package com.github.shazin.cqrs.ms.assignment.command.repo;

import com.github.shazin.cqrs.ms.assignment.command.entity.AssignmentCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentCommandRepository extends JpaRepository<AssignmentCommand, String> {

}
