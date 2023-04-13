package com.github.shazin.cqrs.ms.assignment.query.controller;

import com.github.shazin.cqrs.ms.assignment.dto.Assignment;
import com.github.shazin.cqrs.ms.assignment.query.repo.AssignmentRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin
public class AssignmentQueryController {

    private final AssignmentRepository assignmentRepository;

    public AssignmentQueryController(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @CrossOrigin
    @QueryMapping
    public List<Assignment> allAssignments() {
        return assignmentRepository.findAll().stream().map(assignmentEntity -> new Assignment(assignmentEntity.getId(), assignmentEntity.getDriverId(), assignmentEntity.getTripId(), assignmentEntity.getStatus())).toList();
    }

    @CrossOrigin
    @QueryMapping
    public Assignment findOneAssignment(@Argument String id) {
        return assignmentRepository.findById(id).map(assignmentEntity -> new Assignment(assignmentEntity.getId(), assignmentEntity.getDriverId(), assignmentEntity.getTripId(), assignmentEntity.getStatus())).orElse(null);
    }
}
