package com.backend.banorte.service;

import com.backend.banorte.adapters.TarjetaAdapter;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.request.AccountNumberRequest;
import com.backend.banorte.domain.request.ActualizarCvvRequest;
import com.backend.banorte.domain.request.AgregarTarjetaRequest;
import com.backend.banorte.domain.request.CardNumberRequest;
import com.backend.banorte.domain.response.ReporteTarjetaResponse;
import com.backend.banorte.exceptions.CustomMessageException;
import com.backend.banorte.util.Util;
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
        cardDto.setAccountNumber("100000000001");
        cardDto.setCardNumber("1000000000000001");
        cardDto.setCardType("VISA");
        cardDto.setCvv("123");
        cardDto.setExpireDate("12/24");

        when(adapter.getAccountData("100000000001")).thenReturn(cardDto);



        ResponseEntity<CardDto> response = service.obtenerTarjeta(request);
        CardDto responseCardDto = response.getBody();


        Assertions.assertEquals("100000000001", responseCardDto.getAccountNumber());
        Assertions.assertEquals("1000000000000001", responseCardDto.getCardNumber());
        Assertions.assertEquals("VISA",responseCardDto.getCardType());
        Assertions.assertEquals("123",responseCardDto.getCvv());
        Assertions.assertEquals("12/24",responseCardDto.getExpireDate());
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
        cardDto.setCardNumber("1000000000000001");
        cardDto.setCardType("VISA");
        cardDto.setCvv("123");
        cardDto.setExpireDate("12/24");
        CardDto cardDto1 = new CardDto();
        cardDto1.setAccountNumber("100000000001");
        cardDto1.setCardNumber("1000000000000002");
        cardDto1.setCardType("MASTERCARD");
        cardDto1.setCvv("456");
        cardDto1.setExpireDate("12/24");
        cardDtoList.add(cardDto);
        cardDtoList.add(cardDto1);
        when(adapter.getListAccountData("100000000001")).thenReturn(cardDtoList);
        ResponseEntity<List<CardDto>> response = service.obtenerListTarjeta(request);
        List<CardDto> responseListCardDto = response.getBody();

        Assertions.assertEquals("100000000001", responseListCardDto.get(0).getAccountNumber());
        Assertions.assertEquals("1000000000000001", responseListCardDto.get(0).getCardNumber());
        Assertions.assertEquals("VISA", responseListCardDto.get(0).getCardType());
        Assertions.assertEquals("123", responseListCardDto.get(0).getCvv());
        Assertions.assertEquals("12/24", responseListCardDto.get(0).getExpireDate());


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
    @Test
    public void insertarTarjetaTest(){
        AgregarTarjetaRequest request = new AgregarTarjetaRequest();
        request.setAccountNumber("100000000001");
        request.setCardNumber("1000000000000001");
        request.setCardType("VISA");
        request.setExpireDate("12/24");
        request.setCvv("123");
        ResponseEntity response = service.insertarTarjeta(request);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void borrarTarjetatest(){
        AccountNumberRequest request = new AccountNumberRequest();
        request.setAccountNumber("100000000001");
        ResponseEntity response = service.borrarTarjeta(request);
        Assertions.assertEquals(200, response.getStatusCode().value());

    }

    @Test
    public void reporteTarjetaTest(){
        CardNumberRequest request = new CardNumberRequest();
        request.setCardNumber("1000000000000001");

        CardDto cardDto = new CardDto();
        cardDto.setAccountNumber("100000000001");
        cardDto.setCardNumber("1000000000000001");
        cardDto.setCardType("VISA");
        cardDto.setCvv("123");
        cardDto.setExpireDate("12/24");

        when(adapter.getCardData("1000000000000001")).thenReturn(cardDto);
        when(util.obtenerUltimos4Digitos("100000000001")).thenReturn("0001");
        when(util.enmascararNumeroTarjeta("1000000000000001")).thenReturn("**** **** **** 0001");
        when(util.convertirDateFechaString("12/24")).thenReturn("diciembre 2024");
        when(util.convertirPrimeraLetraMayus("diciembre 2024")).thenReturn("Diciembre 2024");

        ResponseEntity<ReporteTarjetaResponse> response = service.reporteTarjeta(request);

        ReporteTarjetaResponse reporteTarjetaResponse = response.getBody();

        Assertions.assertEquals("**** **** **** 0001",reporteTarjetaResponse.getEnmascararNumero());
        Assertions.assertEquals("0001", reporteTarjetaResponse.getLastDigitsAccount());
        Assertions.assertEquals("Diciembre 2024",reporteTarjetaResponse.getExpireDate());
    }

    @Test
    public
    void reporteTarjetaExceptionTest(){
        CardNumberRequest request = new CardNumberRequest();
        request.setCardNumber("1000000000000001");
        when(adapter.getCardData("1000000000000001")).thenReturn(null);

        CustomMessageException exception = Assertions.assertThrows(CustomMessageException.class,()->service.reporteTarjeta(request));
        Assertions.assertEquals("NO SE ENCONTRÓ EL NUMERO DE CUENTA",exception.getMessage());
    }




}
