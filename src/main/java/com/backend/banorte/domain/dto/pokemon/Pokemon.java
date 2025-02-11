package com.backend.banorte.domain.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pokemon {
    @JsonProperty("name")
    private String name;
    @JsonProperty("order")
    private int order;
    @JsonProperty("weight")
    private int weight;
    @JsonProperty("id")
    private int id;
    @JsonProperty("types")
    private List<Types> types;
    @JsonProperty("abilities")
    private List<Abilities> abilities;

}
