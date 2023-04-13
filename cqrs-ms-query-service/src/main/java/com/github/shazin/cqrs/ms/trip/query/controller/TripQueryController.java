package com.github.shazin.cqrs.ms.trip.query.controller;

import com.github.shazin.cqrs.ms.trip.dto.Trip;
import com.github.shazin.cqrs.ms.trip.query.repo.TripRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TripQueryController {

    private final TripRepository tripRepository;

    public TripQueryController(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @QueryMapping
    public List<Trip> allTrips() {
        return tripRepository.findAll().stream().map(tripEntity -> new Trip(tripEntity.getId(), tripEntity.getPickUpTime(), tripEntity.getPickUpLocation(), tripEntity.getDropOffLocation())).toList();
    }

    @QueryMapping
    public Trip findOneTrip(@Argument String id) {
        return tripRepository.findById(id).map(tripEntity -> new Trip(tripEntity.getId(), tripEntity.getPickUpTime(), tripEntity.getPickUpLocation(), tripEntity.getDropOffLocation())).orElse(null);
    }
}
