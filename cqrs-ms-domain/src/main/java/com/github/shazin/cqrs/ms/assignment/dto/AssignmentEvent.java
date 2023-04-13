package com.github.shazin.cqrs.ms.assignment.dto;

import java.util.Date;

public interface AssignmentEvent {

    default String getType() {
        return "CREATE";
    }

    default Date createdDate() {
        return new Date();
    }

    default String createdBy() {
        return "cqrs-ms-assignment-command";
    }
}
