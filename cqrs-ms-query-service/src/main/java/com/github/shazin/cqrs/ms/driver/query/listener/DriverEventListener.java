package com.github.shazin.cqrs.ms.driver.query.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.driver.dto.Driver;
import com.github.shazin.cqrs.ms.driver.dto.DriverCreateEvent;
import com.github.shazin.cqrs.ms.driver.dto.DriverDeleteEvent;
import com.github.shazin.cqrs.ms.driver.dto.DriverUpdateEvent;
import com.github.shazin.cqrs.ms.driver.query.entity.DriverEntity;
import com.github.shazin.cqrs.ms.driver.query.repo.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class DriverEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(DriverEventListener.class);

    private final ObjectMapper objectMapper;
    private final DriverRepository driverRepository;

    public DriverEventListener(ObjectMapper objectMapper, DriverRepository driverRepository) {
        this.objectMapper = objectMapper;
        this.driverRepository = driverRepository;
    }

    @KafkaListener(topics = "com.github.shazin.cqrs.ms.drivers.json")
    public void consume(String message) {
        try {
            Map<String, String> driverEvent = objectMapper.readValue(message, Map.class);
            if (driverEvent.get("type").equals("CREATE")) {
                DriverCreateEvent driverCreateEvent = objectMapper.readValue(message, DriverCreateEvent.class);
                Driver driver = driverCreateEvent.driver();
                driverRepository.save(new DriverEntity(UUID.randomUUID().toString(), driver.driverName(), driver.identityNumber()));
            } else if (driverEvent.get("type").equals("DELETE")) {
                DriverDeleteEvent driverDeleteEvent = objectMapper.readValue(message, DriverDeleteEvent.class);
                driverRepository.deleteById(driverDeleteEvent.id());
            } else if (driverEvent.get("type").equals("UPDATE")) {
                DriverUpdateEvent driverUpdateEvent = objectMapper.readValue(message, DriverUpdateEvent.class);
                Driver driver = driverUpdateEvent.driver();
                String id = driver.id();
                var driverReferencEntity = driverRepository.getReferenceById(id);
                driverReferencEntity.setDriverName(driver.driverName());
                driverRepository.save(driverReferencEntity);
            }

        } catch (Exception e) {
            LOGGER.error("Error while handling message", e);
        }
    }
}
