package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.MonthlyContracts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyContractsRepository extends JpaRepository<MonthlyContracts, Long> {
}
