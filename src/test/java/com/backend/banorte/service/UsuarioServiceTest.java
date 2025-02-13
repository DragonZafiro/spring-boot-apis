package com.backend.banorte.service;

import com.backend.banorte.adapters.UsuarioAdapter;
import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.domain.request.ActualizarUsuarioRequest;
import com.backend.banorte.domain.request.AgregarUsuarioRequest;
import com.backend.banorte.domain.request.UsuarioRequest;
import com.backend.banorte.domain.response.ReporteAccountResponse;
import com.backend.banorte.exceptions.CustomMessageException;
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
    public void obtenerUsuarioTest(){
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("paquito");

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuario("paquito");
        usuarioDto.setPassword("213213");
        usuarioDto.setOtp("123");
        usuarioDto.setEmail("jeje@gmail.com");
        usuarioDto.setAccountNumber("100000000001");

        when(adapter.getUserData("paquito")).thenReturn(usuarioDto);

        ResponseEntity<Object> response = service.obtenerUsuario(request);

        Assertions.assertEquals(200, response.getStatusCode().value() );

    }
    @Test
    public void obtenerUsuarioExceptionTest(){
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("paquito");

        when(adapter.getUserData("paquito")).thenReturn(null);

        CustomMessageException exception = Assertions.assertThrows(CustomMessageException.class, () -> service.obtenerUsuario(request));

        Assertions.assertEquals("EL USUARIO NO EXISTE", exception.getMessage());
    }

    @Test
    public void generarReporteTest (){

        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("usuario");

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsuario("USUARIO");
        usuarioDto.setEmail("123123123@123123");
        usuarioDto.setAccountNumber("50505050501010");
        when(adapter.getUserData("usuario")).thenReturn(usuarioDto);

        when(util.obtenerUltimos4Digitos("50505050501010")).thenReturn("1010");

        ResponseEntity<Object> response = service.generarReporte(request);

//         200, response.getStatusCode().value()

        ReporteAccountResponse accountResponse = (ReporteAccountResponse) response.getBody();


        Assertions.assertEquals("usuario", accountResponse.getUsername());
        Assertions.assertEquals("123123", accountResponse.getEmailDomain());
        Assertions.assertEquals("1010", accountResponse.getLastDigitsAccount());

    }

    @Test
    public void generarReporteExceptionTest() {
        UsuarioRequest request = new UsuarioRequest();
        request.setUsername("usuario");

        when(adapter.getUserData("usuario")).thenReturn(null);

        CustomMessageException exception = Assertions.assertThrows(CustomMessageException.class, () -> service.generarReporte(request));

        Assertions.assertEquals("EL USUARIO NO EXISTE", exception.getMessage());
    }

    @Test
    public void borrarUsuarioTest(){
        UsuarioRequest  request = new UsuarioRequest();
        request.setUsername("chancho");

        ResponseEntity response = service.borrarUsuario(request);

        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void agregarUsuarioTest(){
        AgregarUsuarioRequest request = new AgregarUsuarioRequest();
        request.setUsuario("gagas");
        request.setPassword("67890");
        request.setOtp("123124");

        ResponseEntity response = service.agregarUsuario(request);

        Assertions.assertEquals(200, response.getStatusCode().value());
    }
    @Test
    public void actualizarUsuarioTest(){
        ActualizarUsuarioRequest request = new ActualizarUsuarioRequest();
        request.setUsername("paquito");
        request.setCodigo("nasdkja1231");

        when(adapter.updateUserOtp("paquito","nasdkja1231")).thenReturn(1);

        ResponseEntity<Object> response = service.actualizarUsuario(request);

        Assertions.assertEquals(200,response.getStatusCode().value());
    }
    @Test
    public void actualizarUsuarioExceptionTest(){
        ActualizarUsuarioRequest request = new ActualizarUsuarioRequest();
        request.setUsername("paquito");
        request.setCodigo("nasdkja1231");

        when(adapter.updateUserOtp("paquito","nasdkja1231")).thenReturn(0);

        CustomMessageException exception = Assertions.assertThrows(CustomMessageException.class,()-> service.actualizarUsuario(request));
        Assertions.assertEquals("EL USUARIO NO EXISTE", exception.getMessage());
    }
}
