package com.backend.banorte.repository;

import com.backend.banorte.domain.entity.PokemonEntrenadorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokemonEntrenadorRepository extends JpaRepository<PokemonEntrenadorEntity,String> {

    @Query("SELECT u FROM PokemonEntrenadorEntity AS u WHERE u.entrenadorId = :entrenadorId")
    PokemonEntrenadorEntity obtenerEntrenadorId (@Param("entrenadorId") int entrenadorId);

    @Query("SELECT u FROM PokemonEntrenadorEntity AS u WHERE u.entrenadorId = :entrenadorId")
    List<PokemonEntrenadorEntity> obtenerListEntrenadorId (@Param("entrenadorId") int entrenadorId);


    @Modifying @Transactional
    @Query(value = "INSERT INTO POKEMON_ENTRENADOR(ENTRENADOR_ID, POKEMON_ID) VALUES (:entrenadorId, :pokemonId)", nativeQuery = true)
    void agregarEntrenadorId(@Param("entrenadorId")int entrenadorId, @Param("pokemonId")int pokemonId);

}
