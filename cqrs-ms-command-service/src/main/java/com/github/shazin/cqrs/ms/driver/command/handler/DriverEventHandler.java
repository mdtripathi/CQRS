package com.github.shazin.cqrs.ms.driver.command.handler;

import com.github.shazin.cqrs.ms.driver.dto.DriverEvent;

public interface DriverEventHandler {
    public boolean publishDriverEvent(DriverEvent event);
}
