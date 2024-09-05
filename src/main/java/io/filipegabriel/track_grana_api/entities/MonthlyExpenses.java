package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_monthly_expenses")
public class MonthlyExpenses implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalMonthlyExpensesValue;

    @OneToMany(mappedBy = "monthlyExpenses")
    private List<ExpensesItem> expensesItems = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "monthlyExpenses")
    private Invoice invoice;

}
