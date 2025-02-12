package com.backend.banorte.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pokemon_entrenador")
public class PokemonEntrenadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column (name= "entrenador_id")
    private int entrenadorId;
    @Column(name = "pokemon_id")
    private int pokemonId;
}
