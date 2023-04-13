package com.github.shazin.cqrs.ms.driver.command.controller;

import com.github.shazin.cqrs.ms.driver.command.dto.DriverInput;
import com.github.shazin.cqrs.ms.driver.command.entity.DriverCommand;
import com.github.shazin.cqrs.ms.driver.command.handler.DriverEventHandler;
import com.github.shazin.cqrs.ms.driver.command.repo.DriverCommandRepository;
import com.github.shazin.cqrs.ms.driver.dto.Driver;
import com.github.shazin.cqrs.ms.driver.dto.DriverCreateEvent;
import com.github.shazin.cqrs.ms.driver.dto.DriverDeleteEvent;
import com.github.shazin.cqrs.ms.driver.dto.DriverUpdateEvent;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class DriverCommandController {

    private final DriverEventHandler driverEventHandler;
    private DriverCommandRepository driverCommandRepository;

    public DriverCommandController(DriverEventHandler driverService, DriverCommandRepository driverCommandRepository) {
        this.driverEventHandler = driverService;
        this.driverCommandRepository = driverCommandRepository;
    }

    @MutationMapping
    public Driver createDriver(@Argument DriverInput driver) {
        Driver driverCreated = new Driver(UUID.randomUUID().toString(), driver.driverName(), driver.identityNumber());
        DriverCreateEvent driverCreateEvent = new DriverCreateEvent(driverCreated);
        driverEventHandler.publishDriverEvent(driverCreateEvent);
        return driverCreated;
    }

    @MutationMapping
    public Driver updateDriver(@Argument DriverInput driver) {

        Driver driverUpdated = new Driver(driver.id(), driver.driverName(), driver.identityNumber());
        DriverUpdateEvent driverUpdateEvent = new DriverUpdateEvent(driverUpdated);
        driverEventHandler.publishDriverEvent(driverUpdateEvent);
        return driverUpdated;
    }

    @MutationMapping
    public String deleteDriver(@Argument String id) {
        DriverDeleteEvent driverDeleteEvent = new DriverDeleteEvent(id);
        driverEventHandler.publishDriverEvent(driverDeleteEvent);
        return id;
    }

}