package com.github.shazin.cqrs.ms.driver.query.repo;

import com.github.shazin.cqrs.ms.driver.query.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DriverRepository extends JpaRepository<DriverEntity, String> {

}
