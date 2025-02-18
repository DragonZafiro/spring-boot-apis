package com.backend.banorte.adapters;

import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.entity.TarjetaEntity;
import com.backend.banorte.repository.TarjetasRepository;
import com.backend.banorte.service.TarjetaService;
import com.backend.banorte.util.Util;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TarjetaAdapterTest {
    @InjectMocks
    TarjetaAdapter tarjetaAdapter;
    @Mock
    TarjetasRepository repository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAccountDataTest (){
        String account = "100000000001";

        TarjetaEntity entity = new TarjetaEntity();
        entity.setAccountNumber("100000000001");
        entity.setCardNumber("1000000000000001");
        entity.setCvv("123");
        entity.setExpireDate("12/25");
        entity.setCardType("VISA");

        when(repository.obtenerTarjetas(account)).thenReturn(entity);

        CardDto cardDto = tarjetaAdapter.getAccountData(account);

        Assertions.assertEquals("100000000001",cardDto.getAccountNumber());
        Assertions.assertEquals("1000000000000001", cardDto.getCardNumber());
        Assertions.assertEquals("123", cardDto.getCvv());
        Assertions.assertEquals("12/25",cardDto.getExpireDate());
        Assertions.assertEquals("VISA", cardDto.getCardType());

    }

    @Test
    public void getAccountDataExceptionTest(){

        CardDto cardDto= tarjetaAdapter.getAccountData(null);
        Assertions.assertEquals(null,cardDto);

    }

    @Test
    public void getListAccountDataTest(){
        String account ="100000000001";
        List<TarjetaEntity> tarjetaEntitieList = new ArrayList<>();
        TarjetaEntity tarjetaEntity = new TarjetaEntity();
        tarjetaEntity.setAccountNumber("100000000001");
        tarjetaEntity.setCardNumber("1000000000000001");
        tarjetaEntity.setCardType("VISA");
        tarjetaEntity.setCvv("123");
        tarjetaEntity.setExpireDate("12/24");
        TarjetaEntity tarjetaEntity1 = new TarjetaEntity();
        tarjetaEntity1.setAccountNumber("100000000001");
        tarjetaEntity1.setCardNumber("1000000000000002");
        tarjetaEntity1.setCardType("MASTERCARD");
        tarjetaEntity1.setCvv("456");
        tarjetaEntity1.setExpireDate("12/24");
        tarjetaEntitieList.add(tarjetaEntity);
        tarjetaEntitieList.add(tarjetaEntity1);

        when(repository.obtenerListaTarjetas(account)).thenReturn(tarjetaEntitieList);

        List<CardDto> cardDto = tarjetaAdapter.getListAccountData(account);
        Assertions.assertEquals("100000000001",cardDto.get(0).getAccountNumber());
        Assertions.assertEquals("1000000000000001", cardDto.get(0).getCardNumber());
        Assertions.assertEquals("123", cardDto.get(0).getCvv());
        Assertions.assertEquals("12/24",cardDto.get(0).getExpireDate());
        Assertions.assertEquals("VISA", cardDto.get(0).getCardType());
    }
    @Test
    public void getListAccountDataExceptionTest(){
        List<CardDto> cardDtos = tarjetaAdapter.getListAccountData("999999999");
        Assertions.assertEquals(0,cardDtos.size());

    }

    @Test
    public void getCardDataTest (){
        String card= "1000000000000001";
        TarjetaEntity entity = new TarjetaEntity();
        entity.setAccountNumber("100000000001");
        entity.setCardNumber("1000000000000001");
        entity.setCvv("123");
        entity.setExpireDate("12/25");
        entity.setCardType("VISA");

        when(repository.obtenerCardNumber(card)).thenReturn(entity);

        CardDto cardDto = tarjetaAdapter.getCardData(card);

        Assertions.assertEquals("100000000001",cardDto.getAccountNumber());
        Assertions.assertEquals("1000000000000001", cardDto.getCardNumber());
        Assertions.assertEquals("123", cardDto.getCvv());
        Assertions.assertEquals("12/25",cardDto.getExpireDate());
        Assertions.assertEquals("VISA", cardDto.getCardType());
    }

    @Test
    public void getCardDataExceptionTest (){
        CardDto cardDto= tarjetaAdapter.getCardData(null);
        Assertions.assertEquals(null, cardDto);
    }

    @Test
    public void updateCvvTest(){
        String accountNum = "100000000001";
        String cvv = "123";
        when(repository.actualizarCvv(accountNum,cvv)).thenReturn(1);

        int resultado = tarjetaAdapter.updateCvv(accountNum,cvv);
        Assertions.assertEquals(1, resultado);

    }

    @Test
    public void deleteCardTest(){
        String accountNum = "100000000001";
        Mockito.doNothing().when(repository).borrarTarjeta(accountNum);
    }

    @Test
    public void  addCardTest(){
        String accountNum ="100000000001";
        String cardNum = "1000000000000001";
        String cvv="123";
        String expireDate="getExpireDate";
        String cardType="VISA";

        Mockito.doNothing().when(repository).agregarTarjeta(accountNum,cardNum,cvv,expireDate,cardType);
    }


}
