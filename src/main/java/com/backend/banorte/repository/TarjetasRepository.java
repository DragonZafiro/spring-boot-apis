package com.backend.banorte.repository;

import com.backend.banorte.domain.entity.TarjetaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetasRepository extends JpaRepository<TarjetaEntity, String> {

    @Query("SELECT u FROM TarjetaEntity AS u WHERE u.accountNumber = :accountNumber ")
    TarjetaEntity obtenerTarjetas(@Param("accountNumber")String accountNumber);

    @Query("SELECT u FROM TarjetaEntity AS u WHERE u.accountNumber = :accountNumber ")
    List<TarjetaEntity> obtenerListaTarjetas(@Param("accountNumber")String accountNumber);

    @Query("SELECT u FROM TarjetaEntity AS u WHERE u.cardNumber = :cardNumber ")
    TarjetaEntity obtenerCardNumber(@Param("cardNumber")String cardNumber);

    @Modifying @Transactional
    @Query("UPDATE TarjetaEntity u SET u.cvv = :cvv WHERE u.accountNumber = :accountNumber")
    int actualizarCvv (@Param("accountNumber")String accountNumber, @Param("cvv") String cvv);

    @Modifying @Transactional
    @Query("DELETE FROM TarjetaEntity u WHERE u.accountNumber = :accountNumber")
    void borrarTarjeta (@Param("accountNumber")String accountNumber);

    @Modifying @Transactional
    @Query(value = "INSERT INTO TARJETAS (ACCOUNT_NUMBER,CARD_NUMBER,CVV,EXPIRE_DATE,CARD_TYPE) VALUES(:accountNumber,:cardNumber,:cvv,:expireDate,:cardType)",nativeQuery = true)
    void agregarTarjeta(@Param("accountNumber")String accountNumber,@Param("cardNumber")String cardNumber,@Param("cvv") String cvv, @Param("expireDate")String expireDate,@Param("cardType")String cardType);

}
