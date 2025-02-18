package com.backend.banorte.adapters;

import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.entity.TarjetaEntity;
import com.backend.banorte.repository.TarjetasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarjetaAdapter {
    @Autowired
    TarjetasRepository repository;

    public CardDto getAccountData (String account) {
        TarjetaEntity entity = repository.obtenerTarjetas(account);
        if (entity == null){
            return null;
        }
        CardDto cardDto = new CardDto();
        cardDto.setAccountNumber(entity.getAccountNumber());
        cardDto.setCardNumber(entity.getCardNumber());
        cardDto.setCvv(entity.getCvv());
        cardDto.setExpireDate(entity.getExpireDate());
        cardDto.setCardType(entity.getCardType());
        return cardDto;
    }

    public List<CardDto> getListAccountData (String account){
        List<TarjetaEntity> entityList = repository.obtenerListaTarjetas(account);

        System.out.println("TarjetaEntity list: " + entityList);

        if (entityList == null){
            return null;
        }
        List<CardDto> cardDtosList = new ArrayList<>();
            for (TarjetaEntity tarjetaEntity : entityList) {
                CardDto cardDto = new CardDto();

                cardDto.setAccountNumber(tarjetaEntity.getAccountNumber());
                cardDto.setCardNumber(tarjetaEntity.getCardNumber());
                cardDto.setCvv(tarjetaEntity.getCvv());
                cardDto.setExpireDate(tarjetaEntity.getExpireDate());
                cardDto.setCardType(tarjetaEntity.getCardType());
                cardDtosList.add(cardDto);
            }
        return cardDtosList;
    }


    public CardDto getCardData (String card) {
        TarjetaEntity entity = repository.obtenerCardNumber(card);
        if (entity == null){
            return null;
        }
        CardDto cardDto = new CardDto();
        cardDto.setAccountNumber(entity.getAccountNumber());
        cardDto.setCardNumber(entity.getCardNumber());
        cardDto.setCvv(entity.getCvv());
        cardDto.setExpireDate(entity.getExpireDate());
        cardDto.setCardType(entity.getCardType());
        return cardDto;
    }

    public int updateCvv (String accountNum, String cvv){
        return repository.actualizarCvv(accountNum,cvv);
    }

    public void deleteCard (String accountNum){

        repository.borrarTarjeta(accountNum);
    }

    public void addCard (String accountNum,String cardNum, String cvv, String expireDat, String cardType){
        repository.agregarTarjeta(accountNum,cardNum,cvv,expireDat,cardType);
    }

}
