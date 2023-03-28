package com.professional.dummy.model;

import com.professional.dummy.enums.HttpStatusCode;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class Exception {
    private final String message;
    private final Throwable throwable;
    private final int httpStatusCode;
    private final ZonedDateTime dateTime;

    public Exception(String message, Throwable throwable, int httpStatusCode, ZonedDateTime dateTime) {
        this.message = message;
        this.throwable = throwable;
        this.httpStatusCode = httpStatusCode;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public ZonedDateTime getDateTime() {
        return dateTime;
    }
}
