package com.github.shazin.cqrs.ms.trip.command.repo;

import com.github.shazin.cqrs.ms.trip.command.entity.TripCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripCommandRepository extends JpaRepository<TripCommand, String> {

}
