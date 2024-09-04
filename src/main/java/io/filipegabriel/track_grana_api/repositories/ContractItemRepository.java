package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.ContractItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractItemRepository extends JpaRepository<ContractItem, Long> {
}
