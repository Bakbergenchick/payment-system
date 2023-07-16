package com.silkpay.paymentservice.exception;

public class CardNotFoundException extends RuntimeException{

    public CardNotFoundException(String message) {
        super(message);
    }
}
