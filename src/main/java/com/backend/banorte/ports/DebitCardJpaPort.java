package com.backend.banorte.ports;

import com.backend.banorte.domain.dto.CardDto;

import java.util.List;

public interface DebitCardJpaPort {
    List<CardDto> getCardList (String accountNumber);

}
