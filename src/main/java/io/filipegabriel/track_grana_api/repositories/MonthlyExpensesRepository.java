package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.MonthlyExpenses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyExpensesRepository extends JpaRepository<MonthlyExpenses, Long> {
}
