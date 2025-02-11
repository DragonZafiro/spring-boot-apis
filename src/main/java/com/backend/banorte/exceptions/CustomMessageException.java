package com.backend.banorte.exceptions;


public class CustomMessageException extends CustomException {

    public CustomMessageException(String message) {
        super(message, 500);
    }
    public CustomMessageException(String message, int code) {
        super(message, code);
    }
}