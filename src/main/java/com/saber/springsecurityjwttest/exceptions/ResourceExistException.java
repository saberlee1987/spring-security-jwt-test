package com.saber.springsecurityjwttest.exceptions;

public class ResourceExistException extends RuntimeException{
    private String resource;

    public ResourceExistException(String resource) {
        this.resource = resource;
    }

    public ResourceExistException(String message, String resource) {
        super(message);
        this.resource = resource;
    }

    public ResourceExistException(String message, Throwable cause, String resource) {
        super(message, cause);
        this.resource = resource;
    }

    public ResourceExistException(Throwable cause, String resource) {
        super(cause);
        this.resource = resource;
    }

    public ResourceExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String resource) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }
}
