package com.backend.banorte.service;

import com.backend.banorte.adapters.EntrenadorAdapter;
import com.backend.banorte.adapters.PokemonEntrenadorAdapter;
import com.backend.banorte.adapters.rest.PokemonAdapter;
import com.backend.banorte.domain.dto.EntrenadorDto;
import com.backend.banorte.domain.dto.PokemonEntrenadorDto;
import com.backend.banorte.domain.dto.pokemon.Pokemon;
import com.backend.banorte.domain.request.CapturarPokemonRequest;
import com.backend.banorte.domain.request.NombreEntrenadorRequest;
import com.backend.banorte.domain.response.PokeCaptuPorEntrenadorResponse;
import com.backend.banorte.domain.response.PokemonInfoResponse;
import com.backend.banorte.domain.response.PokemonResponse;
import com.backend.banorte.exceptions.CustomMessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonService {

    @Autowired
    PokemonAdapter pokemonAdapter;

    @Autowired
    EntrenadorAdapter entrenadorAdapter;

    @Autowired
    PokemonEntrenadorAdapter pokemonEntrenadorAdapter;

    public ResponseEntity<PokemonResponse> capturarPokemon (@RequestBody CapturarPokemonRequest request){
        EntrenadorDto entrenadorDto = entrenadorAdapter.obtenerEntrenador(request.getNombreEntrenador());

        if (entrenadorDto == null) {
            throw new CustomMessageException("El entrenador no está registrado");
        }
        Pokemon  pokemon = pokemonAdapter.obtenerPokemon(request.getPokemon());
        if (pokemon == null) {
            throw new CustomMessageException("No se encontró el pokemon a capturar");
        }

        pokemonEntrenadorAdapter.agregarEntrenadorId(entrenadorDto.getId(), pokemon.getId());

        PokemonResponse response = new PokemonResponse();
        response.setMensaje("Pokemon capturado");
        response.setNumeroPokedex(pokemon.getOrder());
        response.setNombrePokemon(pokemon.getName().toUpperCase());




        return ResponseEntity.ok().body(response);
    }


    public ResponseEntity<Object> obtenerListPokeCaptu (@RequestBody NombreEntrenadorRequest request){
        EntrenadorDto entrenadorDto = entrenadorAdapter.obtenerEntrenador(request.getNombre());
        if (entrenadorDto == null) {
            throw new CustomMessageException("El entrenador no está registrado");
        }
        List<PokemonEntrenadorDto> entrenadorDtoList = pokemonEntrenadorAdapter.obtenerListEntrenadorId(entrenadorDto.getId());
        if (entrenadorDtoList == null){
            throw new CustomMessageException("No se encontro el ID del entrenador");
        }

        PokeCaptuPorEntrenadorResponse response = new PokeCaptuPorEntrenadorResponse();
        response.setMensaje("Pokemon Capturados por el entrenador");
        response.setEntrenador(entrenadorDto.getNombre());

        List<PokemonInfoResponse> pokemonCapturador = new ArrayList<>();
        for (PokemonEntrenadorDto pokemonEntrenadorDto :entrenadorDtoList){

            String pokemonIdString = String.valueOf( pokemonEntrenadorDto.getPokemonId());
            Pokemon pokemon = pokemonAdapter.obtenerPokemon(pokemonIdString);

            PokemonInfoResponse pokemonInfoResponse = new PokemonInfoResponse();
            pokemonInfoResponse.setNumeroPokedex(pokemon.getOrder());
            pokemonInfoResponse.setNombrePokemon(pokemon.getName());
            pokemonCapturador.add(pokemonInfoResponse);
        }
        response.setPokemonsCapturados(pokemonCapturador);

        return ResponseEntity.ok(response);
    }


}
