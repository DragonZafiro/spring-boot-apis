package com.backend.banorte.util;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class Util {
    public String obtenerUltimos4Digitos(String numeroCuenta) {

        int primerIndice = numeroCuenta.length() - 4;
        int ultimoIndice = numeroCuenta.length() ;
        String ultimosDigitos = numeroCuenta.substring(primerIndice, ultimoIndice);
        return ultimosDigitos;
    }


    public String convertirDateFechaString(String fecha){
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("MM/yy");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("MMMM yyyy");

        Date fechadate;
        try {
            fechadate = formatoEntrada.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        String fechaFormateada = formatoSalida.format(fechadate).toUpperCase();
        return fechaFormateada;
    }

    public String convertirPrimeraLetraMayus (String palabra){
        String primeraLetra = palabra.substring(0,1).toUpperCase();
        String restoPalabra = palabra.substring(1).toLowerCase();
        String primeraMinuscula = primeraLetra +restoPalabra;
        return primeraMinuscula;

    }

    public String enmascararNumeroTarjeta(String tarjeta){

        String ultimosDigitos = tarjeta.substring(12, 16);
        String nuevoNumTarj = "**** **** **** " + ultimosDigitos;
        return nuevoNumTarj;

    }

}
