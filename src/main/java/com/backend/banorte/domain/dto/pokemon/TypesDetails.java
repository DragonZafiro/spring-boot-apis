package com.backend.banorte.domain.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypesDetails {
    @JsonProperty("name")
    private String name;

}
