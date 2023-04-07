package com.github.shazin.cqrs.ms.user.command.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record DriverInput(String id, String driverName, String identityNumber) implements Serializable {}