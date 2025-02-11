package com.backend.banorte.domain.response;

import com.backend.banorte.domain.dto.CardDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarjetasListResponse {
    @JsonProperty("tarjetas")
    private List<TarjetasListAddResponse> tarjetasList;


}
