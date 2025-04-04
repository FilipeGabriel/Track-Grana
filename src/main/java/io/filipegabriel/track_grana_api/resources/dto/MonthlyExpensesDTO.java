package io.filipegabriel.track_grana_api.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyExpensesDTO {

    private Double totalMonthlyExpensesValue;

    private Long expensesItemsId;

}
