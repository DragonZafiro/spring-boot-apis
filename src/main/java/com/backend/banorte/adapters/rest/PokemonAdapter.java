package com.backend.banorte.adapters.rest;
import com.backend.banorte.domain.dto.pokemon.Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonAdapter {

    @Autowired
    RestTemplate restTemplate;

    @Value("${poke-api.host}")
    String pokeApiHost;


    public Pokemon obtenerPokemon (String nombrePokemon){

        String url = pokeApiHost + "/api/v2/pokemon/"+nombrePokemon;
        ResponseEntity<Pokemon> responseEntity = restTemplate.getForEntity(url, Pokemon.class);
        Pokemon pokemon = responseEntity.getBody();
        return pokemon;
    }


    


}