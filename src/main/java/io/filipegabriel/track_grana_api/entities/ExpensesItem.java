package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "tb_expenses_item")
public class ExpensesItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer installment;

    @Column(nullable = false)
    private Double itemValue;

    @ManyToOne
    @JoinColumn(name = "spent_type_id")
    private SpentType spentType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "monthly_expenses_id")
    private MonthlyExpenses monthlyExpenses;

}
