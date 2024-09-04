package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tb_contract_item")
public class ContractItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double itemValue;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;                       //Criar no front um alarm para informar que faltar tantos dias para o vencimento do item

    @Column(nullable = false)
    private Boolean paid;

    @ManyToOne
    @JoinColumn(name = "spent_type_id")
    private SpentType spentType;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "monthly_contracts_id")
    private MonthlyContracts monthlyContracts;

}
