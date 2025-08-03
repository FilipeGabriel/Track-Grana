package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "monthly_expenses_id")
    private MonthlyExpenses monthlyExpenses;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
        name = "invoice_spent",
        joinColumns = @JoinColumn(name = "invoice_id"),
        inverseJoinColumns = @JoinColumn(name = "spent_type_id")
    )
    private List<SpentType> spentTypes = new ArrayList<>();

}
