package com.backend.banorte.controller;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.entity.TarjetaEntity;
import com.backend.banorte.domain.request.AccountNumberRequest;
import com.backend.banorte.domain.request.ActualizarCvvRequest;
import com.backend.banorte.domain.request.AgregarTarjetaRequest;
import com.backend.banorte.domain.request.CardNumberRequest;
import com.backend.banorte.domain.response.ReporteTarjetaResponse;
import com.backend.banorte.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TarjetaController {

    @Autowired
    TarjetaService tarjetaService;


    @PostMapping("/tarjeta/obtener")
    public ResponseEntity<CardDto> obtenerTarjeta(@RequestBody AccountNumberRequest request){
        return tarjetaService.obtenerTarjeta(request);
    }

    @PostMapping("/tarjeta/obtenerList")
    public ResponseEntity<List<CardDto>> obtenerListTarjeta(@RequestBody AccountNumberRequest request){
        return tarjetaService.obtenerListTarjeta(request);
    }

    @PostMapping("/tarjeta/actualizarCvv")
    public ResponseEntity<Object> actualizarCvv (@RequestBody ActualizarCvvRequest request){
        return tarjetaService.actualizarCvv(request);
    }

    @PostMapping("/tarjeta/insertar")
    public ResponseEntity<TarjetaEntity> insertarTarjeta (@RequestBody AgregarTarjetaRequest request){
        return tarjetaService.insertarTarjeta(request);
    }

    @PostMapping("/tarjeta/borrar")
    public ResponseEntity<TarjetaEntity> borrarTarjeta (@RequestBody AccountNumberRequest request){
        return tarjetaService.borrarTarjeta(request);
    }

    @PostMapping("/tarjeta/reporte")
    public  ResponseEntity<ReporteTarjetaResponse> reporteTarjeta (@RequestBody CardNumberRequest request){
        return tarjetaService.reporteTarjeta(request);
    }


}
