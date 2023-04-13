package com.github.shazin.cqrs.ms.driver.dto;

public record DriverUpdateEvent(Driver driver) implements DriverEvent {

    @Override
    public String getType() {
        return "UPDATE";
    }

}
