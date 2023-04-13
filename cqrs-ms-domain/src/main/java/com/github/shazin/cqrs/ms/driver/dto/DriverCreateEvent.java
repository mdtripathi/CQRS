package com.github.shazin.cqrs.ms.driver.dto;

public record DriverCreateEvent(Driver driver) implements DriverEvent {

    @Override
    public String getType() {
        return "CREATE";
    }

}
