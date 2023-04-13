package com.github.shazin.cqrs.ms.trip.command.handler;

import com.github.shazin.cqrs.ms.trip.dto.TripEvent;

public interface TripEventHandler {
    public boolean publishTripEvent(TripEvent event);
}
