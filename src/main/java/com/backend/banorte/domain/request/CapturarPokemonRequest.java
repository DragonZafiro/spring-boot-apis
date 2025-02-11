package com.backend.banorte.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CapturarPokemonRequest {
    @JsonProperty("entrenador")
    private String nombreEntrenador;
    @JsonProperty("pokemon")
    private String pokemon;
}
