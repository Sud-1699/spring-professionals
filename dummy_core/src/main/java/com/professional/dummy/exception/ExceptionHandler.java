package com.professional.dummy.exception;

import com.professional.dummy.enums.HttpStatusCode;
import com.professional.dummy.model.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { BadException.class })
    public ResponseEntity<Object> handlerException(BadException e) {
        //1. create payload contain exception details
        Exception exception = new Exception(
                e.getMessage(),
                e.getCause(),
                HttpStatusCode.INVALID_REQUEST.getStatusCode(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        //2. Return response entity
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }
}
