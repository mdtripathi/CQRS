package com.github.shazin.cqrs.ms.trip.dto;

import java.io.Serializable;
import java.time.OffsetTime;

public record Trip(String id, OffsetTime pickUpTime, String pickUpLocation, String dropOffLocation) implements Serializable {}

