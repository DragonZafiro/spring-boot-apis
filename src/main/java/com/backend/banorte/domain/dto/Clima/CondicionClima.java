package com.backend.banorte.domain.dto.Clima;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CondicionClima {
    @JsonProperty("text")
    private String text;
}
