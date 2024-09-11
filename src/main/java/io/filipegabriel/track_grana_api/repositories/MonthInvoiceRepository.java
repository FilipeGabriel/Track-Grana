package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.MonthInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthInvoiceRepository extends JpaRepository<MonthInvoice, Long> {
}
