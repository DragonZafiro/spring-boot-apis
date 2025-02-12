package com.backend.banorte.repository;


import com.backend.banorte.domain.entity.EntrenadorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EntrenadorRepository extends JpaRepository<EntrenadorEntity,String>{

    @Query("SELECT u FROM EntrenadorEntity AS u WHERE u.nombre = :nombre")
    EntrenadorEntity obtenerNombreEntrenador (@Param("nombre")String nombre);

    @Query("SELECT u FROM EntrenadorEntity AS u WHERE u.nombre = :nombre")
    List<EntrenadorEntity> obtenerListNombreEntrenador (@Param("nombre")String nombre);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ENTRENADOR (NOMBRE,PASSWORD) VALUES (:nombre, :password)", nativeQuery = true)
    void agregarEntrenador (@Param("nombre")String nombre,@Param("password")String password);

}
