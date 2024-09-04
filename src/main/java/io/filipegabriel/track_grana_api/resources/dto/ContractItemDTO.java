package io.filipegabriel.track_grana_api.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractItemDTO {

    private String description;

    private Double itemValue;

    private String endDate;

    private Boolean paid;

    private Long spentTypeId;

    private Long monthlyContractsId;

}
