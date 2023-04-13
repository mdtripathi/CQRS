package com.github.shazin.cqrs.ms.trip.dto;

public record TripCreateEvent(Trip trip) implements TripEvent {

    @Override
    public String getType() {
        return "CREATE";
    }

}
