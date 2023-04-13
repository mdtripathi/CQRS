package com.github.shazin.cqrs.ms.assignment.command.handler.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.assignment.command.entity.AssignmentCommand;
import com.github.shazin.cqrs.ms.assignment.command.repo.AssignmentCommandRepository;
import com.github.shazin.cqrs.ms.assignment.command.handler.AssignmentEventHandler;
import com.github.shazin.cqrs.ms.assignment.dto.AssignmentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DefaultAssignmentEventHandler implements AssignmentEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultAssignmentEventHandler.class);

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String kafkaTopicName;
    private final ObjectMapper objectMapper;
    private final AssignmentCommandRepository assignmentCommandRepository;


    public DefaultAssignmentEventHandler(KafkaTemplate<String, String> kafkaTemplate, @Value("${kafka.topic.name}") String kafkaTopicName, ObjectMapper objectMapper, AssignmentCommandRepository assignmentCommandRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicName = "com.github.shazin.cqrs.ms.assignments.json";
        this.objectMapper = objectMapper;
        this.assignmentCommandRepository = assignmentCommandRepository;
    }

    public boolean publishAssignmentEvent(AssignmentEvent event) {
        try {
            String payload = objectMapper.writeValueAsString(event);
            this.kafkaTemplate.send(kafkaTopicName, payload);
            this.assignmentCommandRepository.save(new AssignmentCommand(UUID.randomUUID().toString(), payload, event.createdDate(), event.createdBy(), event.getType()));
            return true;
        } catch (Exception e) {
            LOGGER.error("Error while publishing assignment", e);
            return false;
        }
    }


}
