package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_monthly_contracts")
public class MonthlyContracts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalMonthlyContractsValue;

    @OneToMany(mappedBy = "monthlyContracts")
    private List<ContractItem> contractItems = new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "monthlyContracts")
    private Account account;

}
