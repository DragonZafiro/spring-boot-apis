package com.backend.banorte.adapters;

import com.backend.banorte.domain.dto.PokemonEntrenadorDto;
import com.backend.banorte.domain.entity.PokemonEntrenadorEntity;
import com.backend.banorte.repository.PokemonEntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PokemonEntrenadorAdapter {

    @Autowired
    PokemonEntrenadorRepository repository;

    public PokemonEntrenadorDto obtenerEntrenadorId (int entrenadorId){
        PokemonEntrenadorEntity entity = repository.obtenerEntrenadorId(entrenadorId);
        if (entity == null){
            return null;
        }
        PokemonEntrenadorDto pokemonEntrenadorDto = new PokemonEntrenadorDto();
        pokemonEntrenadorDto.setEntrenadorId(entity.getEntrenadorId());
        pokemonEntrenadorDto.setPokemonId(entity.getPokemonId());
        return pokemonEntrenadorDto;
    }


    public List<PokemonEntrenadorDto> obtenerListEntrenadorId (int entrenadorId){
        List<PokemonEntrenadorEntity> entity = repository.obtenerListEntrenadorId(entrenadorId);
        if (entity == null){
            return null;
        }
        List<PokemonEntrenadorDto> entrenadorDtoList = new ArrayList<>();
        for(PokemonEntrenadorEntity pokemonEntrenadorEntity: entity) {
            PokemonEntrenadorDto pokemonEntrenadorDto = new PokemonEntrenadorDto();
            pokemonEntrenadorDto.setEntrenadorId(pokemonEntrenadorEntity.getEntrenadorId());
            pokemonEntrenadorDto.setPokemonId(pokemonEntrenadorEntity.getPokemonId());
            entrenadorDtoList.add(pokemonEntrenadorDto);
        }
        return entrenadorDtoList;
    }

    public void agregarEntrenadorId (int entrenadorId, int pokemonId){
        repository.agregarEntrenadorId(entrenadorId,pokemonId);
    }


}
