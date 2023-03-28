package com.professional.dummy.exception;

public class BadException extends RuntimeException{

    public BadException(String message) {
        super(message);
    }

    public BadException(String message, Throwable cause) {
        super(message, cause);
    }
}
