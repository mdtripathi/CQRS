package com.github.shazin.cqrs.ms.assignment.dto;

public record AssignmentCreateEvent(Assignment assignment) implements AssignmentEvent {

    @Override
    public String getType() {
        return "CREATE";
    }

}
