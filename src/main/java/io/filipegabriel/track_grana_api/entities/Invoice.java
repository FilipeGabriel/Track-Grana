package io.filipegabriel.track_grana_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_invoice")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalInvoiceValue;

    private Boolean totalPaid;

    @OneToOne
    @JoinColumn(name = "month_invoice_id", unique = true)
    private MonthInvoice monthInvoice;

    @OneToOne
    @JoinColumn(name = "monthly_contracts_id")
    private MonthlyContracts monthlyContracts;

    @OneToOne
    @JoinColumn(name = "monthly_expenses_id")
    private MonthlyExpenses monthlyExpenses;

}
