package com.backend.banorte.controller;

import com.backend.banorte.adapters.rest.PokemonAdapter;
import com.backend.banorte.domain.dto.pokemon.Pokemon;
import com.backend.banorte.domain.request.CapturarPokemonRequest;
import com.backend.banorte.domain.request.NombreEntrenadorRequest;
import com.backend.banorte.domain.response.PokemonResponse;
import com.backend.banorte.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class PokemonController {
    @Autowired
    PokemonAdapter pokemonAdapter;

    @Autowired
    PokemonService pokemonService;


    @GetMapping("/pokemon/{nombrePokemon}")
    public Pokemon nombrePokemon (@PathVariable String nombrePokemon ){
        return pokemonAdapter.obtenerPokemon(nombrePokemon);
    }

    @PostMapping("/pokemon/capturar")
    public ResponseEntity<PokemonResponse> capturarPokemon (@RequestBody CapturarPokemonRequest request){
        return pokemonService.capturarPokemon(request);
    }

    @PostMapping("/pokemon/listaPokemonCapturados")
    public ResponseEntity<Object> obtenerListaPokemonCapturados (@RequestBody NombreEntrenadorRequest request){
        return pokemonService.obtenerListPokeCaptu(request);
    }

}
