package com.backend.banorte.controller;

import com.backend.banorte.domain.dto.AccountDto;
import com.backend.banorte.domain.dto.CardDto;
import com.backend.banorte.domain.request.GetUserCardListRequest;
import com.backend.banorte.ports.AccountServicePort;
import com.backend.banorte.ports.DebitCardServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//anotaciones @
@RestController
public class DebitCardController {


    @Autowired
    DebitCardServicePort debitCardServicePort;

    @Autowired
    AccountServicePort accountServicePort;




    @PostMapping("/card/list")
    public ResponseEntity<List<CardDto>> getCardList (@RequestBody GetUserCardListRequest request) {
        System.out.println("request = " + request);
        return debitCardServicePort.getCardList(request);
    }

    @PostMapping("/account/list")
    public ResponseEntity<List<AccountDto>> getAccountList(
            @RequestHeader(name = "Authorization") String auth,
            @RequestBody GetUserCardListRequest request){

        System.out.println(" Authirization = " + auth);
        System.out.println("request = " + request);
        return accountServicePort.getAccountList(request, auth);
    }







}
