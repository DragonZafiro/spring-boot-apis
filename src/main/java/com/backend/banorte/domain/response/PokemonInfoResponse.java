package com.backend.banorte.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonInfoResponse {
    @JsonProperty("numero_pokedex")
    private int numeroPokedex;

    @JsonProperty("nombre_pokemon")
    private String nombrePokemon;
}
