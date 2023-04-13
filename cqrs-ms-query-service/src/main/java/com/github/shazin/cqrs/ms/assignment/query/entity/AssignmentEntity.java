package com.github.shazin.cqrs.ms.assignment.query.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "cqrs_assignment")
public class AssignmentEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String driverId;
    private String tripId;
    private String status;

    public AssignmentEntity() {
    }

    public AssignmentEntity(String id, String driverId, String tripId, String status) {
        this.id = id;
        this.driverId = driverId;
        this.tripId = tripId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentEntity that = (AssignmentEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(driverId, that.driverId) && Objects.equals(tripId, that.tripId) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
