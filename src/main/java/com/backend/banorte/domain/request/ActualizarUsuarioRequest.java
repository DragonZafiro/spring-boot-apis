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
public class ActualizarUsuarioRequest {

    @JsonProperty("usuario")
    private String username;

    @JsonProperty("otp")
    private String codigo;
}
