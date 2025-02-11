package com.backend.banorte.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokemonResponse {
    @JsonProperty("mensaje")
    private String mensaje;
    @JsonProperty("numero_pokedex")
    private int numeroPokedex;
    @JsonProperty("nombre_pokemon")
    private String nombrePokemon;





}
