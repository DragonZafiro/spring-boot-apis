package com.backend.banorte.ports;

import com.backend.banorte.domain.dto.AccountDto;
import com.backend.banorte.domain.dto.CardDto;

import java.util.List;

public interface AccountJpaPort {
    List<AccountDto> getCardList (String accountNumber);
}
