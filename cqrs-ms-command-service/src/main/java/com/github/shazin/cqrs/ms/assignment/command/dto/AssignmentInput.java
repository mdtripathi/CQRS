package com.github.shazin.cqrs.ms.assignment.command.dto;

import java.io.Serializable;

public record AssignmentInput(String id, String driverId, String tripId, String status) implements Serializable {}

