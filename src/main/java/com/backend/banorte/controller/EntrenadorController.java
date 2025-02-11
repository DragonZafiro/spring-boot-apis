package com.backend.banorte.controller;


import com.backend.banorte.domain.entity.EntrenadorEntity;
import com.backend.banorte.domain.request.AgregarEntrenadorRequest;
import com.backend.banorte.domain.request.NombreEntrenadorRequest;
import com.backend.banorte.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
public class EntrenadorController {
    @Autowired
    EntrenadorService entrenadorService;

    @PostMapping("/entrenador/obtener")
    public ResponseEntity<Object> obtenerEntrenador(@RequestBody NombreEntrenadorRequest request){
        return entrenadorService.obtenerEntrenador(request);
    }

    @PostMapping("/entrenador/insertar")
    public ResponseEntity<EntrenadorEntity> agregarEntrenador(@RequestBody AgregarEntrenadorRequest request){
        return entrenadorService.agregarEntrenador(request);
    }


}
