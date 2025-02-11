package com.backend.banorte.adapters.jpa.anterior;

import com.backend.banorte.domain.entity.UsuarioEntity;
import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserJpaAdapter {



    @Autowired
    UsuarioRepository usuarioRepository;


    public UsuarioDto obtenerUsuario(String usuario) {
        UsuarioEntity usuarioEntity= usuarioRepository.obtenerUsuario(usuario);


        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuario(usuarioEntity.getUsuario());
        usuarioDto.setPassword(usuarioEntity.getPassword());

        return usuarioDto;
    }

    public UsuarioDto actualizarOtp (UsuarioDto dto){
    int actualizarOtp = usuarioRepository.actualizarOtp(dto.getUsuario(), dto.getOtp());


    return dto;
    }

    public  UsuarioDto deleteUsuario (String usuario) {



        usuarioRepository.borrarUsuario(usuario);


        return null;
    }

    public UsuarioDto agregarUsuario (String usuario,String password, String otp){

        usuarioRepository.agregarUsuario(usuario,password,otp);

        return null;
    }



}
