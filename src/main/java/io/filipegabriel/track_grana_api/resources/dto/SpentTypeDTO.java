package io.filipegabriel.track_grana_api.resources.dto;

import io.filipegabriel.track_grana_api.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpentTypeDTO {

    private String name;

    private Double totalBankValue;

    private Boolean paid;

    private Long unitItemsId;

    private Long ContractItemsId;

    private Long accountId;

}
