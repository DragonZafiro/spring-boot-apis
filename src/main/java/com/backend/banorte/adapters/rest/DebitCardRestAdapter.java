package com.backend.banorte.adapters.rest;

import com.backend.banorte.adapters.jpa.anterior.UserJpaAdapter;
import com.backend.banorte.domain.dto.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class DebitCardRestAdapter {

    @Autowired
    UserJpaAdapter userJpaAdapter;

    public boolean loginValido (String auth){
        String codificado = auth.replaceAll("Basic ", "");
        byte[] bytesDecodificados = Base64.getDecoder().decode(codificado);
        String cadenaDecodificada = new String(bytesDecodificados);
        String [] parts = cadenaDecodificada.split(":");

        String usuario = parts[0];
        String password = parts[1];

        UsuarioDto usuarioDto = userJpaAdapter.obtenerUsuario(usuario);

        if (usuarioDto.getUsuario().equals(usuario) && usuarioDto.getPassword().equals(password)){
            return true;
        }
        return false;
    }


}