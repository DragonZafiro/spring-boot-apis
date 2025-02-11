package com.backend.banorte.domain.dto.pokemon;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Abilities {
    @JsonProperty("ability")
    private Ability ability;
    @JsonProperty ("is_hidden")
    private boolean isHidden;
    @JsonProperty("slot")
    private int slot;


}
