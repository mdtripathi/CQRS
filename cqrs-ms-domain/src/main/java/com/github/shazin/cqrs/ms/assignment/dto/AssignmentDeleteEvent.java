package com.github.shazin.cqrs.ms.assignment.dto;

public record AssignmentDeleteEvent(String id) implements AssignmentEvent {

    @Override
    public String getType() {
        return "DELETE";
    }
}
