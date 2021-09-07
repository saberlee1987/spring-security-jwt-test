package com.saber.springsecurityjwttest.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private String resource;

    public ResourceNotFoundException(String resource) {
        this.resource = resource;
    }

    public ResourceNotFoundException(String message, String resource) {
        super(message);
        this.resource = resource;
    }

    public ResourceNotFoundException(String message, Throwable cause, String resource) {
        super(message, cause);
        this.resource = resource;
    }

    public ResourceNotFoundException(Throwable cause, String resource) {
        super(cause);
        this.resource = resource;
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String resource) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
