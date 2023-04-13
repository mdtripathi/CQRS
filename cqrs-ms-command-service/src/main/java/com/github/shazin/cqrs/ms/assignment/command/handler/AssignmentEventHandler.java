package com.github.shazin.cqrs.ms.assignment.command.handler;

import com.github.shazin.cqrs.ms.assignment.dto.AssignmentEvent;

public interface AssignmentEventHandler {
    public boolean publishAssignmentEvent(AssignmentEvent event);
}
