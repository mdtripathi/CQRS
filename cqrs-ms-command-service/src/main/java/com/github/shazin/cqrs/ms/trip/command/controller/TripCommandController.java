package com.github.shazin.cqrs.ms.trip.command.controller;

import com.github.shazin.cqrs.ms.trip.command.dto.TripInput;
import com.github.shazin.cqrs.ms.trip.command.handler.TripEventHandler;
import com.github.shazin.cqrs.ms.trip.dto.Trip;
import com.github.shazin.cqrs.ms.trip.dto.TripCreateEvent;
import com.github.shazin.cqrs.ms.trip.dto.TripUpdateEvent;
import com.github.shazin.cqrs.ms.trip.dto.TripDeleteEvent;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class TripCommandController {

    private final TripEventHandler tripEventHandler;

    public TripCommandController(TripEventHandler tripService) {
        this.tripEventHandler = tripService;
    }

    @MutationMapping
    public Trip createTrip(@Argument TripInput trip) {
        Trip tripCreated = new Trip(UUID.randomUUID().toString(), trip.pickUpTime(), trip.pickUpLocation(), trip.dropOffLocation());
        TripCreateEvent tripCreateEvent = new TripCreateEvent(tripCreated);
        tripEventHandler.publishTripEvent(tripCreateEvent);
        return tripCreated;
    }

    @MutationMapping
    public Trip updateTrip(@Argument TripInput trip) {

        Trip tripUpdated = new Trip(trip.id(), trip.pickUpTime(), trip.pickUpLocation(), trip.dropOffLocation());
        TripUpdateEvent tripUpdateEvent = new TripUpdateEvent(tripUpdated);
        tripEventHandler.publishTripEvent(tripUpdateEvent);
        return tripUpdated;
    }

    @MutationMapping
    public String deleteTrip(@Argument String id) {
        TripDeleteEvent tripDeleteEvent = new TripDeleteEvent(id);
        tripEventHandler.publishTripEvent(tripDeleteEvent);
        return id;
    }

}