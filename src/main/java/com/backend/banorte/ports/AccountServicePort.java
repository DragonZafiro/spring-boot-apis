package com.backend.banorte.ports;

import com.backend.banorte.domain.dto.AccountDto;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.request.GetUserCardListRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountServicePort {
    ResponseEntity<List<AccountDto>> getAccountList(GetUserCardListRequest request,String auth);

}
