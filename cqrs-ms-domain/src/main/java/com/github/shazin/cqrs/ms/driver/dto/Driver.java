package com.github.shazin.cqrs.ms.driver.dto;

import java.io.Serializable;

public record Driver(String id, String driverName, String identityNumber) implements Serializable {}

