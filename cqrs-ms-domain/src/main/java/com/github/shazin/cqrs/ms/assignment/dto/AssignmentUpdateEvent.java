package com.github.shazin.cqrs.ms.assignment.dto;

public record AssignmentUpdateEvent(Assignment assignment) implements AssignmentEvent {

    @Override
    public String getType() {
        return "UPDATE";
    }

}
