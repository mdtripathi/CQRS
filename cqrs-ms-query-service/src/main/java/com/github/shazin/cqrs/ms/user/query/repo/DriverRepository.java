package com.github.shazin.cqrs.ms.user.query.repo;
import com.github.shazin.cqrs.ms.user.query.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverRepository extends JpaRepository<DriverEntity, String> {
}