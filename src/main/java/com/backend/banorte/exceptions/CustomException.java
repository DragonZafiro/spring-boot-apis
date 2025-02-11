package com.backend.banorte.exceptions;

public class CustomException extends RuntimeException{


    public CustomException(String message,int code) {
        super(message);
    }
}
