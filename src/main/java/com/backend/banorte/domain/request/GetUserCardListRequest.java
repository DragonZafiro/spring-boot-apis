package com.backend.banorte.domain.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class GetUserCardListRequest {


    @JsonProperty("account_number")
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
