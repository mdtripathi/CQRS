package com.github.shazin.cqrs.ms.trip.query.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shazin.cqrs.ms.trip.dto.Trip;
import com.github.shazin.cqrs.ms.trip.dto.TripCreateEvent;
import com.github.shazin.cqrs.ms.trip.dto.TripDeleteEvent;
import com.github.shazin.cqrs.ms.trip.dto.TripUpdateEvent;
import com.github.shazin.cqrs.ms.trip.query.entity.TripEntity;
import com.github.shazin.cqrs.ms.trip.query.repo.TripRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class TripEventListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(TripEventListener.class);

    private final ObjectMapper objectMapper;
    private final TripRepository tripRepository;

    public TripEventListener(ObjectMapper objectMapper, TripRepository tripRepository) {
        this.objectMapper = objectMapper;
        this.tripRepository = tripRepository;
    }

    @KafkaListener(topics = "com.github.shazin.cqrs.ms.trips.json")
    public void consume(String message) {
        try {
            Map<String, String> tripEvent = objectMapper.readValue(message, Map.class);
            if (tripEvent.get("type").equals("CREATE")) {
                TripCreateEvent tripCreateEvent = objectMapper.readValue(message, TripCreateEvent.class);
                Trip trip = tripCreateEvent.trip();
                tripRepository.save(new TripEntity(UUID.randomUUID().toString(), trip.pickUpTime(), trip.pickUpLocation(), trip.dropOffLocation()));
            } else if (tripEvent.get("type").equals("DELETE")) {
                TripDeleteEvent tripDeleteEvent = objectMapper.readValue(message, TripDeleteEvent.class);
                tripRepository.deleteById(tripDeleteEvent.id());
            } else if (tripEvent.get("type").equals("UPDATE")) {
                TripUpdateEvent tripUpdateEvent = objectMapper.readValue(message, TripUpdateEvent.class);

                Trip trip = tripUpdateEvent.trip();
                String id = trip.id();

                var tripReferencEntity = tripRepository.getReferenceById(id);
                tripReferencEntity.setPickUpTime(trip.pickUpTime());
                tripReferencEntity.setPickUpLocation(trip.pickUpLocation());
                tripReferencEntity.setDropOffLocation(trip.dropOffLocation());
                tripRepository.save(tripReferencEntity);
            }

        } catch (Exception e) {
            LOGGER.error("Error while handling message", e);
        }
    }
}
