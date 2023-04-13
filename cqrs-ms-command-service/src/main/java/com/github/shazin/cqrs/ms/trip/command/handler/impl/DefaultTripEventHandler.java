package com.github.shazin.cqrs.ms.trip.command.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.trip.command.entity.TripCommand;
import com.github.shazin.cqrs.ms.trip.command.repo.TripCommandRepository;
import com.github.shazin.cqrs.ms.trip.command.handler.TripEventHandler;
import com.github.shazin.cqrs.ms.trip.dto.TripEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultTripEventHandler implements TripEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTripEventHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopicName;
    private final ObjectMapper objectMapper;
    private final TripCommandRepository tripCommandRepository;


    public DefaultTripEventHandler(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.topic.name}") String kafkaTopicName, ObjectMapper objectMapper, TripCommandRepository tripCommandRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicName = "com.github.shazin.cqrs.ms.trips.json";
        this.objectMapper = objectMapper;
        this.tripCommandRepository = tripCommandRepository;
    }

    public boolean publishTripEvent(TripEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            this.kafkaTemplate.send(kafkaTopicName, payload);
            this.tripCommandRepository.save(new TripCommand(UUID.randomUUID().toString(), payload, event.createdDate(), event.createdBy(), event.getType()));
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while publishing trip", e);
            return false;
        }
    }


}
