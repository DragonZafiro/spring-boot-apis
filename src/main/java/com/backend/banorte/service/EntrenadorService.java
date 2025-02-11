package com.backend.banorte.service;

import com.backend.banorte.adapters.EntrenadorAdapter;
import com.backend.banorte.domain.dto.EntrenadorDto;
import com.backend.banorte.domain.entity.EntrenadorEntity;
import com.backend.banorte.domain.request.AgregarEntrenadorRequest;
import com.backend.banorte.domain.request.NombreEntrenadorRequest;
import com.backend.banorte.domain.response.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Base64;

@Service
public class EntrenadorService {



    @Autowired
    EntrenadorAdapter entrenadorAdapter;



    public ResponseEntity<Object> obtenerEntrenador(@RequestBody NombreEntrenadorRequest request) {
        EntrenadorDto entity = entrenadorAdapter.obtenerEntrenador(request.getNombre());
        if (entity == null) {
            GenericResponse data = new GenericResponse();
            data.setMessage("Entrenador no encontrado");
        }
        System.out.println("El nombre del entrenador es: " + entity);
        return ResponseEntity.ok().body(entity);

    }

    public ResponseEntity<EntrenadorEntity> agregarEntrenador(@RequestBody AgregarEntrenadorRequest request){
        String codificarPassword = codificarPassword(request.getPassword());
        entrenadorAdapter.agregarNameYPassEntrenador(request.getNombre(), codificarPassword);
        System.out.println("Se inserto el entrenador correctamente");
        return ResponseEntity.ok().build();
    }


    public String codificarPassword (String password){
        return Base64.getEncoder().encodeToString(password.getBytes());
    }
}
