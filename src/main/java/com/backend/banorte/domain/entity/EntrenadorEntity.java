package com.backend.banorte.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="entrenador")
public class EntrenadorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="password")
    private String password;
}
