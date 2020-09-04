package com.equinix.serviceprofile.exception;

public class ProcessingException extends RuntimeException {

    public ProcessingException() {
        super();
    }

    public ProcessingException(Exception e) {
        super(e);
    }
}
