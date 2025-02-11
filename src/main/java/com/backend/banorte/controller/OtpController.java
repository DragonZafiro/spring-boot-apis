package com.backend.banorte.controller;

import com.backend.banorte.adapters.TarjetaAdapter;
import com.backend.banorte.adapters.UsuarioAdapter;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.dto.UsuarioDto;
import com.backend.banorte.domain.request.ActualizarCvvRequest;
import com.backend.banorte.domain.request.ActualizarUsuarioRequest;
import com.backend.banorte.domain.request.UserPasswordRequest;
import com.backend.banorte.domain.request.UsuarioRequest;
import com.backend.banorte.domain.response.GenericResponse;
import com.backend.banorte.domain.response.OtpResponse;
import com.backend.banorte.domain.response.TarjetasListAddResponse;
import com.backend.banorte.domain.response.TarjetasListResponse;
import com.backend.banorte.exceptions.CustomMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class OtpController {
    @Autowired
    UsuarioAdapter usuarioAdapter;

    @Autowired
    TarjetaAdapter tarjetaAdapter;

    @PostMapping("/otp/generar")
    public ResponseEntity<Object> RandomOtp(@RequestBody UserPasswordRequest request){
        System.out.println(" SE CAMBIARA EL OTP DONDE EL USUARIO SEA: " + request.getUsuario());

        UsuarioDto validarUsuarioPassword = usuarioAdapter.getUserData(request.getUsuario());

        if (validarUsuarioPassword == null) {
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }
        if (!validarUsuarioPassword.getPassword().equals(request.getPassword())){
            throw new CustomMessageException("CONTRASEÑA INCORRECTA");
        }
        String nuevoOtp = generarRandomOtp();
        usuarioAdapter.updateUserOtp(request.getUsuario(), nuevoOtp);

        OtpResponse response = new OtpResponse();
        response.setOtp(nuevoOtp);




        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/otp/borrar")
    public ResponseEntity<Object> burrarOtp(@RequestBody ActualizarUsuarioRequest request){
        System.out.println(" SE CAMBIARA EL OTP DONDE EL USUARIO SEA: " + request.getUsername());

        UsuarioDto validarUsuarioOtp = usuarioAdapter.getUserData(request.getUsername());

        if (validarUsuarioOtp == null){
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }
        if (!validarUsuarioOtp.getOtp().equals(request.getCodigo())){
            throw new CustomMessageException("EL OTP ES INVALIDO");
        }

        usuarioAdapter.updateUserOtp(request.getUsername(), null);

        System.out.println("otp validado");

        return  ResponseEntity.ok().body(validarUsuarioOtp);

    }

    @PostMapping("/buscar/usuarioTarjeta")
    public ResponseEntity<Object> buscarTarjetaUsuario (@RequestBody UsuarioRequest request){
        UsuarioDto entity = usuarioAdapter.getUserData(request.getUsername());
        if (entity == null) {
            throw new CustomMessageException("EL USUARIO NO EXISTE");
        }
        List<CardDto> entityCardDto = tarjetaAdapter.getListAccountData(entity.getAccountNumber());


        if (entityCardDto == null) {
            throw new CustomMessageException("NO SE ENCONTRÓ LA TARJETA PARA EL USUARIO");
        }

        List<TarjetasListAddResponse> tarjetasList = new ArrayList<>();
        for (CardDto card: entityCardDto){
            String convertirExpireDate = convertirDateFechaString(card.getExpireDate());
            String convertirFecha = convertirPrimeraLetraMayus(convertirExpireDate);
            tarjetasList.add(new TarjetasListAddResponse(card.getCardNumber(), convertirFecha));
        }



        TarjetasListResponse response = new TarjetasListResponse();
        response.setTarjetasList(tarjetasList);


    return ResponseEntity.ok().body(response);
    }




    private String generarRandomOtp (){
        Random random = new Random();
        int numeroOtp = 100000 + random.nextInt(999999);
        System.out.println(numeroOtp);
        return String.valueOf(numeroOtp);
    }

    private String convertirDateFechaString(String fecha) {
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("MM/yy");
        SimpleDateFormat formatoSalida = new SimpleDateFormat("MMMM yyyy");
        Date fechadate = null;
        try {
            fechadate = formatoEntrada.parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String fechaFormateada = formatoSalida.format(fechadate).toUpperCase();
        return fechaFormateada;
    }
    private String convertirPrimeraLetraMayus (String palabra){
        String primeraLetra = palabra.substring(0,1).toUpperCase();
        String restoPalabra = palabra.substring(1).toLowerCase();
        String primeraMinuscula = primeraLetra +restoPalabra;
        return primeraMinuscula;

    }
}
