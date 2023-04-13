package com.github.shazin.cqrs.ms.trip.dto;

public record TripDeleteEvent(String id) implements TripEvent {

    @Override
    public String getType() {
        return "DELETE";
    }
}
