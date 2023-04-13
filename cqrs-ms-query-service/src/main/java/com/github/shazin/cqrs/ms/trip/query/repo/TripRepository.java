package com.github.shazin.cqrs.ms.trip.query.repo;

import com.github.shazin.cqrs.ms.trip.query.entity.TripEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TripRepository extends JpaRepository<TripEntity, String> {

}
