package com.github.shazin.cqrs.ms.driver.command.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.driver.command.entity.DriverCommand;
import com.github.shazin.cqrs.ms.driver.command.repo.DriverCommandRepository;
import com.github.shazin.cqrs.ms.driver.command.handler.DriverEventHandler;
import com.github.shazin.cqrs.ms.driver.dto.DriverEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultDriverEventHandler implements DriverEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDriverEventHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopicName;
    private final ObjectMapper objectMapper;
    private final DriverCommandRepository driverCommandRepository;


    public DefaultDriverEventHandler(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.topic.name}") String kafkaTopicName, ObjectMapper objectMapper, DriverCommandRepository driverCommandRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicName = "com.github.shazin.cqrs.ms.drivers.json";
        this.objectMapper = objectMapper;
        this.driverCommandRepository = driverCommandRepository;
    }

    public boolean publishDriverEvent(DriverEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            this.kafkaTemplate.send(kafkaTopicName, payload);
            this.driverCommandRepository.save(new DriverCommand(UUID.randomUUID().toString(), payload, event.createdDate(), event.createdBy(), event.getType()));
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while publishing driver", e);
            return false;
        }
    }


}
