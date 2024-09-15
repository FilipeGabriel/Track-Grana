package io.filipegabriel.track_grana_api.resources.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> noSuchElement(NoSuchElementException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Id não encontrado";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.CONFLICT;
        String error = "Houve algum erro ao tentar realizar o procedimento. Verifique!";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request){
        Instant timestamp = Instant.now();
        HttpStatus status = HttpStatus.CONFLICT;
        String error = "Exceção de argumento ilegal. Verifique!";
        String message = e.getMessage();
        String path = request.getRequestURI();
        StandardError standardError = new StandardError(timestamp, status.value(), error, message, path);
        return ResponseEntity.status(status).body(standardError);
    }

}
