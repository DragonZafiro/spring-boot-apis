package com.backend.banorte.service;

import com.backend.banorte.adapters.TarjetaAdapter;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.request.AccountNumberRequest;
import com.backend.banorte.domain.request.ActualizarCvvRequest;
import com.backend.banorte.exceptions.CustomMessageException;
import com.backend.banorte.util.Util;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class TarjetaServiceTest {
    @InjectMocks
    TarjetaService service;
    @Mock
    TarjetaAdapter adapter;
    @Mock
    Util util;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void obtenerTarjetaTest(){
        AccountNumberRequest request = new AccountNumberRequest();
        request.setAccountNumber("100000000001");

        CardDto cardDto = new CardDto();
        cardDto.setCardNumber("100000000001");
        cardDto.setCardNumber("1000000000000001");
        cardDto.setCardType("VISA");
        cardDto.setCvv("123");
        cardDto.setExpireDate("12/24");

        when(adapter.getAccountData("100000000001")).thenReturn(cardDto);
        ResponseEntity<Object> response = service.obtenerTarjeta(request);

        Assertions.assertEquals(200, response.getStatusCode().value() );

    }

    @Test
    public void obtenerTarjetaExceptionTest(){
        AccountNumberRequest request = new AccountNumberRequest();
        request.setAccountNumber("100000000001");
        when(adapter.getAccountData("100000000001")).thenReturn(null);
        CustomMessageException exception = Assertions.assertThrows(CustomMessageException.class,()->service.obtenerTarjeta(request));
        Assertions.assertEquals("NO SE ENCONTRÓ EL NUMERO DE CUENTA",exception.getMessage());

    }

    @Test
    public void obtenerListTarjetasTest(){
        AccountNumberRequest request = new AccountNumberRequest();
        request.setAccountNumber("100000000001");

        List<CardDto> cardDtoList = new ArrayList<>();
        CardDto cardDto = new CardDto();
        cardDto.setAccountNumber("100000000001");
        cardDto.setCardNumber("100000000001");
        cardDto.setCardNumber("1000000000000001");
        cardDto.setCardType("VISA");
        cardDto.setCvv("123");
        cardDto.setExpireDate("12/24");
        CardDto cardDto1 = new CardDto();
        cardDto1.setAccountNumber("100000000001");
        cardDto1.setCardNumber("100000000002");
        cardDto1.setCardNumber("1000000000000002");
        cardDto1.setCardType("MASTERCARD");
        cardDto1.setCvv("456");
        cardDto1.setExpireDate("12/24");
        cardDtoList.add(cardDto);
        cardDtoList.add(cardDto1);
        when(adapter.getListAccountData("100000000001")).thenReturn(cardDtoList);
        ResponseEntity<Object> response = service.obtenerListTarjeta(request);

        Assertions.assertEquals(200, response.getStatusCode().value() );
    }

    @Test
    public void obtenerListTarjetasExceptionTest(){
        AccountNumberRequest request = new AccountNumberRequest();
        request.setAccountNumber("100000000001");

        when(adapter.getListAccountData("100000000001")).thenReturn(null);
        CustomMessageException exception = Assertions.assertThrows(CustomMessageException.class,()->service.obtenerListTarjeta(request));
        Assertions.assertEquals("NO SE ENCONTRÓ EL NUMERO DE CUENTA", exception.getMessage());
    }

    @Test
    public void actualizarCvvTest(){
        ActualizarCvvRequest request = new ActualizarCvvRequest();
        request.setAccountNumber("100000000001");
        request.setCvv("123");

        when(adapter.updateCvv("100000000001","123")).thenReturn(1);
        ResponseEntity<Object> response = service.actualizarCvv(request);
        Assertions.assertEquals(200,response.getStatusCode().value() );

    }
    @Test
    public void actualizarCvvExceptionTest(){
        ActualizarCvvRequest request = new ActualizarCvvRequest();
        request.setAccountNumber("100000000001");
        request.setCvv("123");
        when(adapter.updateCvv("100000000001","123")).thenReturn(0);
        CustomMessageException exception = Assertions.assertThrows(CustomMessageException.class, ()->service.actualizarCvv(request));
        Assertions.assertEquals("EL USUARIO NO EXISTE",exception.getMessage());
    }

}
