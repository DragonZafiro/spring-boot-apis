package com.backend.banorte.controller;



import com.backend.banorte.domain.entity.UsuarioEntity;
import com.backend.banorte.domain.request.ActualizarUsuarioRequest;
import com.backend.banorte.domain.request.AgregarUsuarioRequest;
import com.backend.banorte.domain.request.UsuarioRequest;

import com.backend.banorte.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuario/obtener")
    public ResponseEntity<Object> obtenerUsuario(@RequestBody UsuarioRequest request) {
        return usuarioService.obtenerUsuario(request);
    }

    @PostMapping("/usuario/actualizar")
    public ResponseEntity<Object> actualizarUsuario(@RequestBody ActualizarUsuarioRequest request) {
        return usuarioService.actualizarUsuario(request);
    }

    @PostMapping("/usuario/insertar")
    public ResponseEntity<UsuarioEntity> agregarUsuario(@RequestBody AgregarUsuarioRequest request) {
        return usuarioService.agregarUsuario(request);
    }
/*
    @PostMapping("/usuario/insertar/jpa")
    public ResponseEntity<UsuarioEntity> agregarUsuarioJpa(@RequestBody AgregarUsuarioRequest request) {

        System.out.println("INSERTAR EN LA TABLA USUARIO CON LOS DATOS OTP: " + request.getOtp() + " PASSWORD: " + request.getPassword() + " DONDE EL USUARIO SEA: " + request.getUsuario());

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(96);
        entity.setUsuario(request.getUsuario());
        entity.setPassword(request.getPassword());
        entity.setOtp(request.getOtp());

        System.out.println("SE GENERO UN OBJETO ENTITY CON LOS DATOS: " + entity);

        usuarioRepository.save(entity);

        System.out.println("SE INSERTÓ EL USUARIO");
        return ResponseEntity.ok().build();
    }
*/

    @PostMapping("/usuario/borrar")
    public ResponseEntity<UsuarioEntity> borrarUsuario(@RequestBody UsuarioRequest request) {
        return usuarioService.borrarUsuario(request);
    }

    @PostMapping("/usuario/reporte")
    public ResponseEntity<Object> generarReporte(@RequestBody UsuarioRequest request) {
        return usuarioService.generarReporte(request);
    }


}
