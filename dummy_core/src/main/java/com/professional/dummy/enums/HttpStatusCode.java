package com.professional.dummy.enums;

public enum HttpStatusCode {
    OK(200), NEW_RESOURCE_CREATED(201), INVALID_REQUEST(400), NOT_AUTHORIZED_REQUEST(
            401), RESOURCE_NOT_FOUND(404), INTERNAL_SYSTEM_ERROR(500),PRECONDITION_ERROR(414);

    private int statusCode;

    HttpStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
