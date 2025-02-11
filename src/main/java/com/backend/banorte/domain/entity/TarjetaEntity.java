package com.backend.banorte.domain.entity;

import jakarta.persistence.*;
import lombok.*;


@ToString
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tarjetas")
public class TarjetaEntity {


    @Column(name = "account_number")
    private String accountNumber;
    @Id
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "expire_date")
    private String expireDate;
    @Column(name = "card_type")
    private String cardType;


}
