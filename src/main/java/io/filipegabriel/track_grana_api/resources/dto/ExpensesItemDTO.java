package io.filipegabriel.track_grana_api.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpensesItemDTO {

    private String description;

    private Integer installment;

    private Double itemValue;

    private Long spentTypeId;

    private Long monthlyExpensesId;

}
