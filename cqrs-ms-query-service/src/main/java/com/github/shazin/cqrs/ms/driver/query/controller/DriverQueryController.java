package com.github.shazin.cqrs.ms.driver.query.controller;

import com.github.shazin.cqrs.ms.driver.dto.Driver;
import com.github.shazin.cqrs.ms.driver.query.repo.DriverRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DriverQueryController {

    private final DriverRepository driverRepository;

    public DriverQueryController(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @SchemaMapping(typeName = "Query", field = "allDrivers")
    public List<Driver> allDrivers() {
        return driverRepository.findAll().stream().map(driverEntity -> new Driver(driverEntity.getId(), driverEntity.getDriverName(), driverEntity.getIdentityNumber())).toList();
    }

    @SchemaMapping(typeName = "Query", field = "findOneDriver")
    public Driver findOneDriver(@Argument String id) {
        return driverRepository.findById(id).map(driverEntity -> new Driver(driverEntity.getId(), driverEntity.getDriverName(), driverEntity.getIdentityNumber())).orElse(null);
    }
}
