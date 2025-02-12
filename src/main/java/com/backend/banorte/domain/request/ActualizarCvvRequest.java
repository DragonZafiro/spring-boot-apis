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
public class ActualizarCvvRequest {

    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("cvv")
    private String cvv;
}
