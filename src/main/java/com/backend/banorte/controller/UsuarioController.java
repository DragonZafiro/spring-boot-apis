package com.backend.banorte.controller;



import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.domain.entity.UsuarioEntity;
import com.backend.banorte.domain.request.ActualizarUsuarioRequest;
import com.backend.banorte.domain.request.AgregarUsuarioRequest;
import com.backend.banorte.domain.request.EmailPasswordRequest;
import com.backend.banorte.domain.request.UsuarioRequest;

import com.backend.banorte.domain.response.ReporteAccountResponse;
import com.backend.banorte.domain.response.TokenResponse;
import com.backend.banorte.service.UsuarioService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/usuario/obtener")
    public ResponseEntity<UsuarioDto> obtenerUsuario(@RequestBody UsuarioRequest request) {
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
    public ResponseEntity<ReporteAccountResponse> generarReporte(@RequestBody UsuarioRequest request) {
        return usuarioService.generarReporte(request);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<Object> LogintJWT(@RequestBody EmailPasswordRequest request){
        return usuarioService.loginJWT(request);
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validateToken(@RequestHeader ("Authorization")String token){
        try{
            Claims claims = usuarioService.validateToken(token);

            return ResponseEntity.ok().body("Token valido"+ claims);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token no valido"+e.getMessage());
        }
    }

}
