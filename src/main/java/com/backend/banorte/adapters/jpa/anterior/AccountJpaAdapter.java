package com.backend.banorte.adapters.jpa.anterior;

import com.backend.banorte.domain.dto.AccountDto;
import com.backend.banorte.ports.AccountJpaPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountJpaAdapter implements AccountJpaPort {


    @Override
    public List<AccountDto> getCardList(String accountNumber) {
        AccountDto accountDto = new AccountDto();
        accountDto.setFullName("Juan Pablo de la Rosa");
        accountDto.setUsername("JUAN");
        accountDto.setAccountNumber(accountNumber);
        accountDto.setPin("1234");
        accountDto.setEmail("juanPR@gmail.com");
        accountDto.setPhoneNumber("5500000001");
        return List.of(accountDto);
    }
}
