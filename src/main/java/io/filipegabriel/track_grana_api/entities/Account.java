package io.filipegabriel.track_grana_api.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    private String name;

    @Column(unique = true)
    private String cpf;

    private String telephone;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Column(length = 1000000)
    private String accountImage;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id")
    private Users user;

    @OneToMany(mappedBy = "account")
    private List<Invoice> invoices = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<SpentType> spentTypes = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "monthly_contracts_id")
    private MonthlyContracts monthlyContracts;

}
