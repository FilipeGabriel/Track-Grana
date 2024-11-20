package io.filipegabriel.track_grana_api.resources.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private String name;

    private String cpf;

    private String telephone;

    private String birthDate;

    private String accountImage;

    private Long userId;

}
