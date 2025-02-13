package com.backend.banorte.service;

import com.backend.banorte.adapters.UsuarioAdapter;
import com.backend.banorte.adapters.jpa.anterior.UserJpaAdapter;
import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.domain.entity.UsuarioEntity;
import com.backend.banorte.domain.request.ActualizarUsuarioRequest;
import com.backend.banorte.domain.request.AgregarUsuarioRequest;
import com.backend.banorte.domain.request.UsuarioRequest;
import com.backend.banorte.domain.response.GenericResponse;
import com.backend.banorte.domain.response.ReporteAccountResponse;
import com.backend.banorte.exceptions.CustomMessageException;
import com.backend.banorte.util.Util;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Random;

@Service
public class UsuarioService {

    @Autowired
    UsuarioAdapter usuarioAdapter;

    @Autowired
    Util util;

    public ResponseEntity<Object> obtenerUsuario(@RequestBody UsuarioRequest request) {

        System.out.println("CONSULTAR A LA BASE DE DATOS CON EL USUARIO: " + request.getUsername());
        UsuarioDto entity = usuarioAdapter.getUserData(request.getUsername());

        if (entity == null) {
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }

        System.out.println("DATOS DE RESPUESTA: " + entity);
        return ResponseEntity.ok().body(entity);
    }

    public ResponseEntity<Object> generarReporte(UsuarioRequest request) {
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
}
