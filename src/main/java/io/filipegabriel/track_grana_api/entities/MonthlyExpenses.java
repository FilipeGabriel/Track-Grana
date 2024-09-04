package io.filipegabriel.track_grana_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_monthly_expenses")
public class MonthlyExpenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalMonthlyExpensesValue;

    @OneToMany(mappedBy = "monthlyExpenses")
    private List<ExpensesItem> expensesItems = new ArrayList<>();
}
