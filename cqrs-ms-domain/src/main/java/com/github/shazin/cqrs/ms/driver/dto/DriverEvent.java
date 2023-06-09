package com.github.shazin.cqrs.ms.driver.dto;

import java.util.Date;

public interface DriverEvent {

    default String getType() {
        return "CREATE";
    }

    default Date createdDate() {
        return new Date();
    }

    default String createdBy() {
        return "cqrs-ms-driver-command";
    }
}
