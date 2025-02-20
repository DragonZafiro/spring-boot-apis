package com.backend.banorte.adapters.rest;

import com.backend.banorte.domain.dto.Clima.ClimaLocalizacion;
import com.backend.banorte.domain.dto.Clima.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClimaAdapter {

    @Autowired
    RestTemplate restTemplate;
    @Value("${weather-api.host}")
    String apiUrl;
    @Value("${weather-api.key}")
    String apiKey;

    public Location obtenerClima(String nombreCiudad){
        String url =apiUrl+"/v1/current.json?key="+apiKey+"&q="+nombreCiudad+"&aqi=no";
        ResponseEntity<Location> responseEntity = restTemplate.getForEntity(url,Location.class);
       Location location=responseEntity.getBody();
        return location;
    }
}
