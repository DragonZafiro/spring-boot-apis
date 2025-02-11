package com.backend.banorte.domain.dto;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonEntrenadorDto {
    private int id;
    private int entrenadorId;
    private int pokemonId;
}
