package com.github.shazin.cqrs.ms.assignment.dto;

import java.io.Serializable;

public record Assignment(String id, String driverId, String tripId, String status) implements Serializable {}

