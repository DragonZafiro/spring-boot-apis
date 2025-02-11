package com.backend.banorte.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class PokeApiUrl {



    @Value("${poke-api.host}")
    protected String baseUrl;

    public String PokemonUrl(){
        return baseUrl;
    }

}
