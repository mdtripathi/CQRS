package com.github.shazin.cqrs.ms.trip.dto;

import java.util.Date;

public interface TripEvent {

    default String getType() {
        return "CREATE";
    }

    default Date createdDate() {
        return new Date();
    }

    default String createdBy() {
        return "cqrs-ms-trip-command";
    }
}
