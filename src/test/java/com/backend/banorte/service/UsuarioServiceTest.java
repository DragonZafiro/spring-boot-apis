package com.backend.banorte.service;

import com.backend.banorte.adapters.UsuarioAdapter;
import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.domain.request.UsuarioRequest;
import com.backend.banorte.util.Util;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioServiceTest {

    @InjectMocks
    UsuarioService service;

    @Mock
    UsuarioAdapter adapter;

    @Mock
    Util util;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void obtenerUsuarioTest (){
        UsuarioDto mockUsuarioDto = new UsuarioDto();
        mockUsuarioDto.setUsuario("paquito");

        Assertions.assertEquals("paquito1",mockUsuarioDto.getUsuario() );


    }
}
