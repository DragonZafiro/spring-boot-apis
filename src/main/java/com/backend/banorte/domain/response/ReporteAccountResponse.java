package com.backend.banorte.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReporteAccountResponse {

    private String username;
    @JsonProperty("dominio_correo")
    private String emailDomain;
    @JsonProperty("last_4_digits_account")
    private String lastDigitsAccount;
}
