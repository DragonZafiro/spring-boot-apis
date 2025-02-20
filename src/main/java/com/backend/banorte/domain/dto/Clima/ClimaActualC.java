package com.backend.banorte.domain.dto.Clima;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClimaActualC {
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("temp_c")
    private int temperatura;
    @JsonProperty("condition")
    private CondicionClima condicionClimaList;
    @JsonProperty("humidity")
    private int humedad;
    @JsonProperty("cloud")
    private int nubes;
    @JsonProperty("uv")
    private int rayosUV;
}
