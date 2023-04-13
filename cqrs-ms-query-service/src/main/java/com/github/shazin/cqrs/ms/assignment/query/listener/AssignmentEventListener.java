package com.github.shazin.cqrs.ms.assignment.query.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.assignment.dto.Assignment;
import com.github.shazin.cqrs.ms.assignment.dto.AssignmentCreateEvent;
import com.github.shazin.cqrs.ms.assignment.dto.AssignmentDeleteEvent;
import com.github.shazin.cqrs.ms.assignment.dto.AssignmentUpdateEvent;
import com.github.shazin.cqrs.ms.assignment.query.entity.AssignmentEntity;
import com.github.shazin.cqrs.ms.assignment.query.repo.AssignmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class AssignmentEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(AssignmentEventListener.class);

    private final ObjectMapper objectMapper;
    private final AssignmentRepository assignmentRepository;

    public AssignmentEventListener(ObjectMapper objectMapper, AssignmentRepository assignmentRepository) {
        this.objectMapper = objectMapper;
        this.assignmentRepository = assignmentRepository;
    }

    @KafkaListener(topics = "com.github.shazin.cqrs.ms.assignments.json")
    public void consume(String message) {
        try {
            Map<String, String> assignmentEvent = objectMapper.readValue(message, Map.class);
            if (assignmentEvent.get("type").equals("CREATE")) {
                AssignmentCreateEvent assignmentCreateEvent = objectMapper.readValue(message, AssignmentCreateEvent.class);
                Assignment assignment = assignmentCreateEvent.assignment();
                assignmentRepository.save(new AssignmentEntity(UUID.randomUUID().toString(), assignment.driverId(), assignment.tripId(), assignment.status()));
            } else if (assignmentEvent.get("type").equals("DELETE")) {
                AssignmentDeleteEvent assignmentDeleteEvent = objectMapper.readValue(message, AssignmentDeleteEvent.class);
                assignmentRepository.deleteById(assignmentDeleteEvent.id());
            } else if (assignmentEvent.get("type").equals("UPDATE")) {
                AssignmentUpdateEvent assignmentUpdateEvent = objectMapper.readValue(message, AssignmentUpdateEvent.class);
                Assignment assignment = assignmentUpdateEvent.assignment();
                String id = assignment.id();
                var assignmentReferencEntity = assignmentRepository.getReferenceById(id);
                assignmentReferencEntity.setStatus(assignment.status());
                assignmentRepository.save(assignmentReferencEntity);
            }

        } catch (Exception e) {
            LOGGER.error("Error while handling message", e);
        }
    }
}
