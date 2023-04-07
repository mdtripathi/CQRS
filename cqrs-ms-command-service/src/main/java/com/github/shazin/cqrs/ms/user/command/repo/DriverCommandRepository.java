package com.github.shazin.cqrs.ms.user.command.repo;
import com.github.shazin.cqrs.ms.user.command.entity.DriverCommand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverCommandRepository extends JpaRepository<DriverCommand, String> {

}