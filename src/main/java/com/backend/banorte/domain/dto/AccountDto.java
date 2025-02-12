package com.backend.banorte.domain.dto;

import lombok.ToString;

@ToString
public class AccountDto {
    private String email;
    private String fullName;
    private String accountNumber;
    private String pin;
    private String phoneNumber;
    private String username;

    public AccountDto(){

    }

    public AccountDto(String email, String fullName, String accountNumber, String pin, String phoneNumber, String username) {
        this.email = email;
        this.fullName = fullName;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.phoneNumber = phoneNumber;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
