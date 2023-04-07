package com.github.shazin.cqrs.ms.user.command.controller;
import com.github.shazin.cqrs.ms.user.command.dto.DriverInput;
import com.github.shazin.cqrs.ms.user.command.handler.DriverEventHandler;
import com.github.shazin.cqrs.ms.user.dto.Driver;
import com.github.shazin.cqrs.ms.user.dto.DriverCreateEvent;
import com.github.shazin.cqrs.ms.user.dto.DriverDeleteEvent;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class DriverCommandController {

    private final DriverEventHandler driverEventHandler;

    public DriverCommandController(DriverEventHandler driverService) {
        this.driverEventHandler = driverService;
    }

    @MutationMapping
    public Driver createDriver(@Argument DriverInput driver) {
        Driver driverCreated = new Driver(UUID.randomUUID().toString(), driver.driverName(), driver.identityNumber());
        DriverCreateEvent driverCreateEvent = new DriverCreateEvent(driverCreated);
        driverEventHandler.publishDriverEvent(driverCreateEvent);
        return driverCreated;
    }

    @MutationMapping
    public String deleteDriver(@Argument String id) {
        DriverDeleteEvent driverDeleteEvent = new DriverDeleteEvent(id);
        driverEventHandler.publishDriverEvent(driverDeleteEvent);
        return id;
    }

}