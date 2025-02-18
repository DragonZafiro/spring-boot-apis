package com.backend.banorte.domain.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotValitResponse {
    @JsonProperty ("error")
    private String error;
    @JsonProperty("message")
    private String message;
    @JsonProperty("error")
    private Object data;
}
