package io.filipegabriel.track_grana_api.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthInvoiceDTO {

    private String monthYear;

}
