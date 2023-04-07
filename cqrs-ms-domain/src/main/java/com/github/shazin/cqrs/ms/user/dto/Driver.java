package com.github.shazin.cqrs.ms.user.dto;

import java.io.Serializable;
import java.time.LocalDate;

public record Driver(String id, String driverName, String identityNumber) implements Serializable {}