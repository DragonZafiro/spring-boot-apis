package com.backend.banorte.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PokeCaptuPorEntrenadorResponse {
    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("entrenador")
    private String entrenador;

    @JsonProperty("pokemons_capturados")
    private List<PokemonInfoResponse> pokemonsCapturados;
}
