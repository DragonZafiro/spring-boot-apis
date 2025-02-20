package com.backend.banorte.domain.dto.Clima;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClimaLocalizacion {
    @JsonProperty("name")
    private String name;
    @JsonProperty("region")
    private String region;
    @JsonProperty("country")
    private String country;

}
