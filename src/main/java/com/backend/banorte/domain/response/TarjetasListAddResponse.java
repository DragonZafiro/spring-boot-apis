package com.backend.banorte.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TarjetasListAddResponse {
    @JsonProperty("numero_tarjeta")
    private String CardNumber;
    @JsonProperty("fecha_expiracion")
    private String expireDate;
}
