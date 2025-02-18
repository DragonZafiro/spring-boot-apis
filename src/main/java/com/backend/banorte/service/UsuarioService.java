package com.backend.banorte.service;

import com.backend.banorte.adapters.UsuarioAdapter;
import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.domain.entity.UsuarioEntity;
import com.backend.banorte.domain.request.ActualizarUsuarioRequest;
import com.backend.banorte.domain.request.AgregarUsuarioRequest;
import com.backend.banorte.domain.request.EmailPasswordRequest;
import com.backend.banorte.domain.request.UsuarioRequest;

import com.backend.banorte.domain.response.EmailNotValitResponse;
import com.backend.banorte.domain.response.ReporteAccountResponse;
import com.backend.banorte.domain.response.TokenResponse;
import com.backend.banorte.exceptions.CustomMessageException;
import com.backend.banorte.util.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;


@Service
public class UsuarioService {

    @Autowired
    UsuarioAdapter usuarioAdapter;

    @Autowired
    Util util;



    public ResponseEntity<UsuarioDto> obtenerUsuario(@RequestBody UsuarioRequest request) {

        System.out.println("CONSULTAR A LA BASE DE DATOS CON EL USUARIO: " + request.getUsername());
        UsuarioDto entity = usuarioAdapter.getUserData(request.getUsername());

        if (entity == null) {
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }

        System.out.println("DATOS DE RESPUESTA: " + entity);
        return ResponseEntity.ok().body(entity);
    }

    public ResponseEntity<ReporteAccountResponse> generarReporte(UsuarioRequest request) {
        UsuarioDto entity = usuarioAdapter.getUserData(request.getUsername());
        if (entity == null) {
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }

        ReporteAccountResponse response = new ReporteAccountResponse();
        response.setUsername(entity.getUsuario().toLowerCase());
        response.setEmailDomain(entity.getEmail().split("@")[1]);

        String ultimos = util.obtenerUltimos4Digitos(entity.getAccountNumber());
        response.setLastDigitsAccount(ultimos);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<UsuarioEntity> borrarUsuario(@RequestBody UsuarioRequest request) {

        System.out.println("BORRAR EN LA TABLA EL USUARIO CON EL NOMBRE:  " + request.getUsername());
        usuarioAdapter.deleteUser(request.getUsername());
        System.out.println("SE HA BORRADO EL USUARIO");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UsuarioEntity> agregarUsuario(@RequestBody AgregarUsuarioRequest request) {

        System.out.println("INSERTAR EN LA TABLA USUARIO CON LOS DATOS OTP: " + request.getOtp() + " PASSWORD: " + request.getPassword() + " DONDE EL USUARIO SEA: " + request.getUsuario());
        usuarioAdapter.addUser(request.getUsuario(), request.getPassword(), request.getOtp());
        System.out.println("SE INSERTÓ EL USUARIO");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> actualizarUsuario(@RequestBody ActualizarUsuarioRequest request) {

        System.out.println("ACTUALIZAR LA TABLA USUARIO CON LOS DATOS OTP: " + request.getCodigo() + " DONDE EL USUARIO SEA: " + request.getUsername());
        int filasActualizadas = usuarioAdapter.updateUserOtp(request.getUsername(), request.getCodigo());

        if (filasActualizadas == 0) {
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }
        System.out.println("SE ACTUALIZARON UN TOTAL DE " + filasActualizadas + " REGISTROS");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> loginJWT (@RequestBody EmailPasswordRequest request){

        UsuarioDto validarEmailPassword = usuarioAdapter.getEmail(request.getEmail());
        EmailNotValitResponse emailNotValitResponse = new EmailNotValitResponse();
        if (validarEmailPassword == null){
            emailNotValitResponse.setData(400);
            emailNotValitResponse.setError("Bad Request");
            emailNotValitResponse.setMessage("El email no es válido");
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(emailNotValitResponse);
        }
        EmailNotValitResponse passwordNotValitResponse = new EmailNotValitResponse();
        if (!validarEmailPassword.getPassword().equals(request.getPassword())){
            emailNotValitResponse.setData(401);
            emailNotValitResponse.setError("Unauthorized");
            emailNotValitResponse.setMessage("El password no es válido");
            return ResponseEntity.status(HttpStatusCode.valueOf(401)).body(passwordNotValitResponse);
        }
        System.out.println("paso prueba de login");
        String token= getJWTToken(request.getEmail());


        TokenResponse response = new TokenResponse();
        response.setToken(token);

        return ResponseEntity.ok().body(response);
    }



    private String getJWTToken(String username) {
        String secretKey = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);


        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .setClaims(Map.of("username",username))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 30000))
                .signWith(SignatureAlgorithm.HS256, keyBytes).compact();
        System.out.println("hola soy el token :"+token);
        return "Bearer " + token;

    }

    public Claims validateToken(String token) {
        String jwtToken = token.replace("Bearer ", "");
        String secretKey = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9";
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Jwts.parser().setSigningKey(keyBytes).parseClaimsJws(jwtToken).getBody();
    }




}
