package it.nepstherti.msclients.exceptions.handller;


import it.nepstherti.msclients.exceptions.error.StandardError;
import it.nepstherti.msclients.exceptions.error.ValidationError;
import it.nepstherti.msclients.exceptions.exception.BadRequestException;
import it.nepstherti.msclients.exceptions.exception.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException objectNotFoundException) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), objectNotFoundException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardError> badRequestException(BadRequestException badRequestException) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), badRequestException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException integrityViolationException) {
        StandardError errorMessage = new StandardError(System.currentTimeMillis(), HttpStatus.CONFLICT.value(), integrityViolationException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validationError(MethodArgumentNotValidException fieldValidationException) {
        ValidationError error = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),"Field validation error");
        for (FieldError x : fieldValidationException.getBindingResult().getFieldErrors()) {
            error.addErrors(x.getField(), x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
