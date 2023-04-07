package com.github.shazin.cqrs.ms.user.query.entity;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "cqrs_driver")
public class DriverEntity {
    @Id
    private String id = UUID.randomUUID().toString();
    private String driverName;
    private String identityNumber;

    public DriverEntity() {
    }

    public DriverEntity(String id, String driverName, String identityNumber) {
        this.id = id;
        this.driverName = driverName;
        this.identityNumber = identityNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverEntity that = (DriverEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(driverName, that.driverName) && Objects.equals(identityNumber, that.identityNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, driverName, identityNumber);
    }
}
