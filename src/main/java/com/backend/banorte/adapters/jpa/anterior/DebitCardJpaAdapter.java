package com.backend.banorte.adapters.jpa.anterior;

import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.ports.DebitCardJpaPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitCardJpaAdapter implements DebitCardJpaPort {


    @Override
    public List<CardDto> getCardList(String accountNumber) {
        CardDto cardDto1 = new CardDto();
        cardDto1.setAccountNumber(accountNumber);
        cardDto1.setCardNumber("123445567656");
        cardDto1.setCardType("VISA");
        cardDto1.setCvv("223");
        cardDto1.setExpireDate("04/25");

        CardDto cardDto2 = new CardDto();
        cardDto2.setAccountNumber(accountNumber);
        cardDto2.setCardNumber("123445567656");
        cardDto2.setCardType("MASTER CARD");
        cardDto2.setCvv("423");
        cardDto2.setExpireDate("12/28");

        CardDto cardDto3 = new CardDto();
        cardDto3.setAccountNumber(accountNumber);
        cardDto3.setCardNumber("123445567656");
        cardDto3.setCardType("MASTER CARD");
        cardDto3.setCvv("423");
        cardDto3.setExpireDate("12/28");


        return List.of(cardDto1, cardDto2, cardDto3);


    }
}