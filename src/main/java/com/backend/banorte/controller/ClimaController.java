package com.backend.banorte.controller;

import com.backend.banorte.adapters.rest.ClimaAdapter;
import com.backend.banorte.domain.dto.Clima.ClimaLocalizacion;
import com.backend.banorte.domain.dto.Clima.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Validated
@RestController
public class ClimaController {
    @Autowired
    ClimaAdapter climaAdapter;

    @GetMapping("/clima/{nombreCiudad}")
    public Location obtenerClimaCiudad(@PathVariable String nombreCiudad){
        return climaAdapter.obtenerClima(nombreCiudad);
    }

}
