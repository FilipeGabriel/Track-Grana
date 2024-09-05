package io.filipegabriel.track_grana_api.repositories;

import io.filipegabriel.track_grana_api.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
