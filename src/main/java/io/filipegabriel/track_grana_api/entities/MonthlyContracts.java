package io.filipegabriel.track_grana_api.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_monthly_contracts")
public class MonthlyContracts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalMonthlyContractsValue;

    @OneToMany(mappedBy = "monthlyContracts")
    private List<ContractItem> contractItems = new ArrayList<>();

}
