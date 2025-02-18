package com.backend.banorte.service;

import com.backend.banorte.adapters.TarjetaAdapter;
import com.backend.banorte.adapters.UsuarioAdapter;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.entity.TarjetaEntity;
import com.backend.banorte.domain.request.AccountNumberRequest;
import com.backend.banorte.domain.request.ActualizarCvvRequest;
import com.backend.banorte.domain.request.AgregarTarjetaRequest;
import com.backend.banorte.domain.request.CardNumberRequest;
import com.backend.banorte.domain.response.GenericResponse;
import com.backend.banorte.domain.response.ReporteTarjetaResponse;
import com.backend.banorte.exceptions.CustomMessageException;
import com.backend.banorte.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TarjetaService {
    @Autowired
    TarjetaAdapter tarjetaAdapter ;

    @Autowired
    Util util;

    public ResponseEntity<CardDto> obtenerTarjeta(@RequestBody AccountNumberRequest request){
        CardDto entity = tarjetaAdapter.getAccountData(request.getAccountNumber());
        if (entity == null) {
            throw new CustomMessageException("NO SE ENCONTRÓ EL NUMERO DE CUENTA",500);
        }

        return ResponseEntity.ok().body(entity);
    }

    public ResponseEntity<List<CardDto>> obtenerListTarjeta(@RequestBody AccountNumberRequest request){
        System.out.println("CONSULTAR A LA BASE DE DATOS CON EL USUARIO: " + request.getAccountNumber());
        List<CardDto> entity = tarjetaAdapter.getListAccountData(request.getAccountNumber());
        if (entity == null) {
            throw new CustomMessageException("NO SE ENCONTRÓ EL NUMERO DE CUENTA");
        }
        System.out.println("DATOS DE RESPUESTA: " + entity);
        return ResponseEntity.ok().body(entity);
    }

    public ResponseEntity<Object> actualizarCvv (@RequestBody ActualizarCvvRequest request){
        int actualizar = tarjetaAdapter.updateCvv(request.getAccountNumber(), request.getCvv());
        if (actualizar == 0) {
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }
        System.out.println("SE ACTUALIZARON UN TOTAL DE " + actualizar + " REGISTROS");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<TarjetaEntity> insertarTarjeta (@RequestBody AgregarTarjetaRequest request){
        tarjetaAdapter.addCard(request.getAccountNumber(), request.getCardNumber(), request.getCvv(), request.getExpireDate(),request.getCardType());
        System.out.println("SE INSERTÓ LA TARJETA");
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<TarjetaEntity> borrarTarjeta (@RequestBody AccountNumberRequest request){
        System.out.println("BORRAR LA TARJETA CON EL NUMERO DE CUENTA:  " + request.getAccountNumber());
        tarjetaAdapter.deleteCard(request.getAccountNumber());

        System.out.println("SE BORRO LA TARJETA");
        return ResponseEntity.ok().build();
    }

    public  ResponseEntity<ReporteTarjetaResponse> reporteTarjeta (@RequestBody CardNumberRequest request){
        CardDto tarjetaEntity = tarjetaAdapter.getCardData(request.getCardNumber());
        if (tarjetaEntity == null) {
            throw new CustomMessageException("NO SE ENCONTRÓ EL NUMERO DE CUENTA");
        }

        ReporteTarjetaResponse tarjetaResponse = new ReporteTarjetaResponse();
        String obtener4Dig =util.obtenerUltimos4Digitos(tarjetaEntity.getAccountNumber());
        tarjetaResponse.setLastDigitsAccount(obtener4Dig);
        String enmascaNumTarj = util.enmascararNumeroTarjeta(tarjetaEntity.getCardNumber());
        tarjetaResponse.setEnmascararNumero(enmascaNumTarj);
        String formatoFecha = util.convertirDateFechaString(tarjetaEntity.getExpireDate());
        String letrasMinusFecha = util.convertirPrimeraLetraMayus(formatoFecha);
        tarjetaResponse.setExpireDate(letrasMinusFecha);



        return ResponseEntity.ok(tarjetaResponse);

    }


}
