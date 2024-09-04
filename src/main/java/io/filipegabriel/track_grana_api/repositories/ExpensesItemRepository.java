package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.ExpensesItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpensesItemRepository extends JpaRepository<ExpensesItem, Long> {
}
