package com.backend.banorte.adapters.rest;
import com.backend.banorte.config.PokeApiUrl;
import com.backend.banorte.domain.dto.pokemon.Pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonAdapter {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    PokeApiUrl pokeApiUrl;


    public Pokemon obtenerPokemon (String nombrePokemon){

        String url = pokeApiUrl+ "/api/v2/pokemon/"+nombrePokemon;
        ResponseEntity<Pokemon> responseEntity = restTemplate.getForEntity(url, Pokemon.class);
        Pokemon pokemon = responseEntity.getBody();
        return pokemon;
    }


    


}