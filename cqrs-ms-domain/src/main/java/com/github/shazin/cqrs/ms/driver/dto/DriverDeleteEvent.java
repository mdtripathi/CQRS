package com.github.shazin.cqrs.ms.driver.dto;

public record DriverDeleteEvent(String id) implements DriverEvent {

    @Override
    public String getType() {
        return "DELETE";
    }
}
