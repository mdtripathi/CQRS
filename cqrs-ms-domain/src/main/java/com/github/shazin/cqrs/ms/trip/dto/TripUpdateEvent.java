package com.github.shazin.cqrs.ms.trip.dto;

public record TripUpdateEvent(Trip trip) implements TripEvent {

    @Override
    public String getType() {
        return "UPDATE";
    }

}
