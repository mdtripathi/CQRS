package com.github.shazin.cqrs.ms.driver.command.dto;

import java.io.Serializable;

public record DriverInput(String id, String driverName, String identityNumber) implements Serializable {}

