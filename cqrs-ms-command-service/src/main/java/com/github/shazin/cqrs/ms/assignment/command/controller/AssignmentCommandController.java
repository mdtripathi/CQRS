package com.github.shazin.cqrs.ms.assignment.command.controller;

import com.github.shazin.cqrs.ms.assignment.command.dto.AssignmentInput;
import com.github.shazin.cqrs.ms.assignment.command.handler.AssignmentEventHandler;
import com.github.shazin.cqrs.ms.assignment.dto.Assignment;
import com.github.shazin.cqrs.ms.assignment.dto.AssignmentCreateEvent;
import com.github.shazin.cqrs.ms.assignment.dto.AssignmentDeleteEvent;
import com.github.shazin.cqrs.ms.assignment.dto.AssignmentUpdateEvent;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class AssignmentCommandController {

    private final AssignmentEventHandler assignmentEventHandler;

    public AssignmentCommandController(AssignmentEventHandler assignmentService) {
        this.assignmentEventHandler = assignmentService;
    }

    @MutationMapping
    public Assignment assign(@Argument AssignmentInput assignment) {
        Assignment assignmentCreated = new Assignment(UUID.randomUUID().toString(), assignment.driverId(), assignment.tripId(), "REQUESTED");
        AssignmentCreateEvent assignmentCreateEvent = new AssignmentCreateEvent(assignmentCreated);
        assignmentEventHandler.publishAssignmentEvent(assignmentCreateEvent);
        return assignmentCreated;
    }

    @MutationMapping
    public Assignment cancel(@Argument AssignmentInput assignment) {

        Assignment assignmentUpdated = new Assignment(assignment.id(), assignment.driverId(), assignment.tripId(), "CANCELED");
        AssignmentUpdateEvent assignmentUpdateEvent = new AssignmentUpdateEvent(assignmentUpdated);
        assignmentEventHandler.publishAssignmentEvent(assignmentUpdateEvent);
        return assignmentUpdated;
    }

    @MutationMapping
    public Assignment accept(@Argument AssignmentInput assignment) {

        Assignment assignmentUpdated = new Assignment(assignment.id(), assignment.driverId(), assignment.tripId(), "ACCEPTED");
        AssignmentUpdateEvent assignmentUpdateEvent = new AssignmentUpdateEvent(assignmentUpdated);
        assignmentEventHandler.publishAssignmentEvent(assignmentUpdateEvent);
        return assignmentUpdated;
    }

    @MutationMapping
    public Assignment enRoute(@Argument AssignmentInput assignment) {

        Assignment assignmentUpdated = new Assignment(assignment.id(), assignment.driverId(), assignment.tripId(), "EN_ROUTE");
        AssignmentUpdateEvent assignmentUpdateEvent = new AssignmentUpdateEvent(assignmentUpdated);
        assignmentEventHandler.publishAssignmentEvent(assignmentUpdateEvent);
        return assignmentUpdated;
    }

    @MutationMapping
    public Assignment inProgress(@Argument AssignmentInput assignment) {

        Assignment assignmentUpdated = new Assignment(assignment.id(), assignment.driverId(), assignment.tripId(), "IN_PROGRESS");
        AssignmentUpdateEvent assignmentUpdateEvent = new AssignmentUpdateEvent(assignmentUpdated);
        assignmentEventHandler.publishAssignmentEvent(assignmentUpdateEvent);
        return assignmentUpdated;
    }

    @MutationMapping
    public Assignment completed(@Argument AssignmentInput assignment) {

        Assignment assignmentUpdated = new Assignment(assignment.id(), assignment.driverId(), assignment.tripId(), "COMPLETED");
        AssignmentUpdateEvent assignmentUpdateEvent = new AssignmentUpdateEvent(assignmentUpdated);
        assignmentEventHandler.publishAssignmentEvent(assignmentUpdateEvent);
        return assignmentUpdated;
    }

    @MutationMapping
    public String deleteAssignment(@Argument String id) {
        AssignmentDeleteEvent assignmentDeleteEvent = new AssignmentDeleteEvent(id);
        assignmentEventHandler.publishAssignmentEvent(assignmentDeleteEvent);
        return id;
    }

}