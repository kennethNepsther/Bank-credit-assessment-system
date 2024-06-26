package it.nepstherti.mscreditassessment.exception.controller;

import it.nepstherti.mscreditassessment.exception.MicroserviceErrorConnectionException;
import it.nepstherti.mscreditassessment.exception.ObjectNotFoundException;
import it.nepstherti.mscreditassessment.exception.error.StandardError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(MicroserviceErrorConnectionException.class)
    public ResponseEntity<StandardError> restClientException(MicroserviceErrorConnectionException exception) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.SERVICE_UNAVAILABLE.value(),  exception.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorMessage);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException exception) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}

//feign.RetryableException