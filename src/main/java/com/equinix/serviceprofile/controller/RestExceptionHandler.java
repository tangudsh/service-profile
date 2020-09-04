package com.equinix.serviceprofile.controller;

import com.equinix.serviceprofile.exception.InvalidDataException;
import com.equinix.serviceprofile.exception.ProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidDataException.class, IllegalArgumentException.class})
    protected ResponseEntity<Object> handleInvalidDataExceptions(RuntimeException ex, WebRequest request) {

        String bodyOfResponse = "Invalid input arguments";
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {ProcessingException.class})
    protected ResponseEntity<Object> handleProcessingExceptions(RuntimeException ex, WebRequest request) {

        String bodyOfResponse = "Exception while processing request";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
