package com.backend.banorte.adapters;

import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.domain.entity.UsuarioEntity;
import com.backend.banorte.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAdapter {

    @Autowired
    UsuarioRepository repository;


    public UsuarioDto getUserData(String username) {
        UsuarioEntity entity = repository.obtenerUsuario(username);
        if (entity == null) {
            return null;
        }

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuario(entity.getUsuario());
        usuarioDto.setOtp(entity.getOtp());
        usuarioDto.setPassword(entity.getPassword());
        usuarioDto.setEmail(entity.getEmail());
        usuarioDto.setAccountNumber(entity.getAccountNumber());
        return usuarioDto;


    }
    public UsuarioDto getUserPassword(String username, String password) {
        UsuarioEntity entity = repository.obtenerUsuarioPassword(username,password);
        if (entity == null) {
            return null;
        }

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuario(entity.getUsuario());
        usuarioDto.setOtp(entity.getOtp());
        usuarioDto.setPassword(entity.getPassword());
        usuarioDto.setEmail(entity.getEmail());
        usuarioDto.setAccountNumber(entity.getAccountNumber());
        return usuarioDto;


    }


    public int updateUserOtp(String username, String otp) {
        return repository.actualizarOtp(username, otp);
    }

    public int updateUserPasswordOtp(String username,String password, String otp) {
        return repository.actualizarConPasswordOtp(username, password, otp);
    }

    public void deleteUser(String username) {
        repository.borrarUsuario(username);
    }

    public void addUser(String username, String password, String otp) {
        repository.agregarUsuario(username, password, otp);
    }
}
