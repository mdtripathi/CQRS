package com.github.shazin.cqrs.ms.trip.query.entity;

import javax.persistence.*;
import java.util.Objects;
import java.time.OffsetTime;
import java.util.UUID;

@Entity
@Table(name = "cqrs_trip")
public class TripEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private OffsetTime pickUpTime;
    private String pickUpLocation;
    private String dropOffLocation;

    public TripEntity() {
    }

    public TripEntity(String id, OffsetTime pickUpTime, String pickUpLocation, String dropOffLocation) {
        this.id = id;
        this.pickUpTime = pickUpTime;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OffsetTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(OffsetTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpLocation() {
        return pickUpLocation;
    }

    public void setPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
    }

    public String getDropOffLocation() {
        return dropOffLocation;
    }

    public void setDropOffLocation(String dropOffLocation) {
        this.dropOffLocation = dropOffLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripEntity that = (TripEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(pickUpTime, that.pickUpTime) && Objects.equals(pickUpLocation, that.pickUpLocation) && Objects.equals(dropOffLocation, that.dropOffLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pickUpTime, pickUpLocation, dropOffLocation);
    }
}
