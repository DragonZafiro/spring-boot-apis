package com.backend.banorte.domain.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Types {
    @JsonProperty("slot")
    private int slot;
    @JsonProperty("type")
    private TypesDetails type;

}
