package com.backend.banorte.domain.dto.Clima;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {
    @JsonProperty("location")
    private ClimaLocalizacion location;
    @JsonProperty("current")
    private ClimaActualC current;
}
