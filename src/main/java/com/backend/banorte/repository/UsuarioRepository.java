package com.backend.banorte.repository;

import com.backend.banorte.domain.entity.UsuarioEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

    @Query("SELECT u FROM UsuarioEntity AS u WHERE u.usuario = :usuario")
    UsuarioEntity obtenerUsuario(@Param("usuario") String usuario);

    @Query("SELECT u FROM UsuarioEntity AS u WHERE u.email = :email")
    UsuarioEntity obtenerEmail(@Param("email") String email);

    @Query("SELECT u FROM UsuarioEntity AS u WHERE u.usuario = :usuario AND  u.password = :password")
    UsuarioEntity obtenerUsuarioPassword(@Param("usuario") String usuario,@Param("password") String password);

    @Modifying
    @Transactional
    @Query("UPDATE UsuarioEntity u SET u.otp = :otp WHERE u.usuario = :usuario AND u.password = : password ")
    int actualizarConPasswordOtp (@Param("usuario") String usuario,@Param("password")String password,@Param("otp") String otp);

    @Modifying
    @Transactional
    @Query("UPDATE UsuarioEntity u SET u.otp = :otp WHERE u.usuario = :usuario ")
   int actualizarOtp (@Param("usuario") String usuario, @Param("otp") String otp);

    @Modifying @Transactional
    @Query("DELETE FROM UsuarioEntity u WHERE u.usuario = :usuario")
    void borrarUsuario (@Param("usuario")String usuario);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO USUARIOS (USUARIO, PASSWORD, OTP) VALUES (:usuario, :password, :otp)", nativeQuery = true)
    void agregarUsuario(@Param("usuario")String usuario, @Param("password")String password,@Param("otp")String  otp);




    //eliminar e insertar



}
