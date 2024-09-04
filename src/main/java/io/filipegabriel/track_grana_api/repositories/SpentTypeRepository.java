package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.SpentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpentTypeRepository extends JpaRepository<SpentType, Long> {
}
