package com.backend.banorte.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private String usuario;
    private String password;
    private String otp;
    private String email;
    private String accountNumber;

}
