package com.backend.banorte.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPasswordRequest {
    @JsonProperty("usuario")
    private String usuario;
    @JsonProperty("password")
    private String password;
}
