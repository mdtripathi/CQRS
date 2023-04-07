package com.github.shazin.cqrs.ms.user.dto;

public record DriverCreateEvent(Driver driver) implements DriverEvent {

    @Override
    public String getType() {
        return "CREATE";
    }
}