package com.backend.banorte.ports;

import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.request.GetUserCardListRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DebitCardServicePort {
    //verbo o accion mas objeto
    ResponseEntity<List<CardDto>> getCardList(GetUserCardListRequest request);
}
