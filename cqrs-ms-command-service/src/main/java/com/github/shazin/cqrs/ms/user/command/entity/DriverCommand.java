package com.github.shazin.cqrs.ms.user.command.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "cqrs_driver_command")
public class DriverCommand {

    @Id
    private String id = UUID.randomUUID().toString();
    private String payload;
    private Date createdDate;
    private String createdBy;
    private String type;

    public DriverCommand() {
    }

    public DriverCommand(String id, String payload, Date createdDate, String createdBy, String type) {
        this.id = id;
        this.payload = payload;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverCommand that = (DriverCommand) o;
        return Objects.equals(id, that.id) && Objects.equals(payload, that.payload) && Objects.equals(createdDate, that.createdDate) && Objects.equals(createdBy, that.createdBy) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, payload, createdDate, createdBy, type);
    }
}