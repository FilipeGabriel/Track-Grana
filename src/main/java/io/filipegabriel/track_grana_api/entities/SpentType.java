package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_spent_type")
public class SpentType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Double totalBankValue;

    @Column(nullable = false)
    private Boolean paid;

    @JsonIgnore
    @OneToMany(mappedBy = "spentType")
    private List<ExpensesItem> expensesItems = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "spentType")
    private List<ContractItem> ContractItems = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

}
