package com.backend.banorte.domain.dto;

import lombok.*;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntrenadorDto {
    private int id;
    private String nombre;
    private String password;
}
