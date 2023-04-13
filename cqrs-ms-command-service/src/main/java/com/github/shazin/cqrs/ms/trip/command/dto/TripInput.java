package com.github.shazin.cqrs.ms.trip.command.dto;

import java.io.Serializable;
import java.time.OffsetTime;

public record TripInput(String id, OffsetTime pickUpTime, String pickUpLocation, String dropOffLocation) implements Serializable {}

