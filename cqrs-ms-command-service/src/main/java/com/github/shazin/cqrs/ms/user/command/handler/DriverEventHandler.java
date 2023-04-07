package com.github.shazin.cqrs.ms.user.command.handler;
import com.github.shazin.cqrs.ms.user.dto.DriverEvent;

public interface DriverEventHandler {
    public boolean publishDriverEvent(DriverEvent event);
}