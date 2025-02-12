package com.backend.banorte.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AgregarTarjetaRequest {
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty("cvv")
    private String cvv;
    @JsonProperty("expire_date")
    private String expireDate;
    @JsonProperty("card_type")
    private String cardType;
}

