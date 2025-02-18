package com.backend.banorte.util;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;


@RunWith(MockitoJUnitRunner.class)
public class UtilTest {
    @InjectMocks
    Util util;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void obtenerUltimos4DigitosTest(){
        String numeroCuenta = "100000000001";
        String resultado = util.obtenerUltimos4Digitos(numeroCuenta);
        Assertions.assertEquals("0001", resultado);
    }

    @Test
    public void convertirDateFechaStringTest(){
        String fecha = "12/24";
        String fechaConvertida = util.convertirDateFechaString(fecha);
        Assertions.assertEquals("DICIEMBRE 2024", fechaConvertida);

    }

    @Test
    public void convertirDateFechaStringExceptionTest(){
        String fecha = "2025/12";
       Assertions.assertThrows(RuntimeException.class, () -> util.convertirDateFechaString(fecha));

    }

    @Test
    public void convertirPrimeraLetraMayusTest(){
        String palabra = "HOLA";
        String palabraConvertida = util.convertirPrimeraLetraMayus(palabra);
        Assertions.assertEquals("Hola", palabraConvertida);
    }

    @Test
    public void enmascararNumeroTarjetaTest(){
        String tarjeta = "1000000000000001";
        String tarjetaConvertida = util.enmascararNumeroTarjeta(tarjeta);
        Assertions.assertEquals("**** **** **** 0001", tarjetaConvertida);
    }
}
