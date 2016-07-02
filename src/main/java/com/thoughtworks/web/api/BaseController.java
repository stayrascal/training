package com.thoughtworks.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;

public class BaseController {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Exception> handleNpResultException(NoResultException nre) {
        return new ResponseEntity<>(nre, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> handleException(Exception e) {
        return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
