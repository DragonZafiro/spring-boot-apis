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
public class ReporteTarjetaResponse {

    @JsonProperty("last_4_digits_account")
    private String lastDigitsAccount;
    @JsonProperty("marked_card")
    private String enmascararNumero;
    @JsonProperty("expiry_date")
    private String expireDate;

}
