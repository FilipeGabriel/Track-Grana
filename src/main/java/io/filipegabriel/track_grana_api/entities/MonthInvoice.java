package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_month_invoice")
public class MonthInvoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String monthName;

    @Column(unique = true, nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate monthYear;

    @JsonIgnore
    @OneToOne(mappedBy = "monthInvoice")
    private Invoice invoice;

}
