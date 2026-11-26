package com.UndertandCRUD.CRUD.exception;

import com.UndertandCRUD.CRUD.dto.Exceptiondto;
import com.UndertandCRUD.CRUD.dto.Validationexceptionresdto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Validationexceptionresdto> handleMessage(MethodArgumentNotValidException e, HttpServletRequest request) {

        Map<String, String> fielderror = new HashMap<>();
        e.getBindingResult().getFieldErrors()
                .forEach(error -> fielderror.put(error.getField(), error.getDefaultMessage()));

        Validationexceptionresdto exceptiondto = new Validationexceptionresdto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed", // Or pass fielderror here if your DTO accepts a Map
                request.getRequestURI(),
                fielderror // Pass the map into your DTO constructor
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptiondto);
    }


    @ExceptionHandler(ResourceNotfound.class)
    public ResponseEntity<Exceptiondto> handleResourceNotfound(ResourceNotfound e, HttpServletRequest request) {
        Exceptiondto exceptiondto = new Exceptiondto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptiondto);
    }


    @ExceptionHandler(DublicateResourceException.class)
    public ResponseEntity<Exceptiondto> handleDuplicateResourceNotfound(DublicateResourceException e , HttpServletRequest request) {

        Exceptiondto exceptiondto = new Exceptiondto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exceptiondto);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Exceptiondto> handleRunException(RuntimeException e , HttpServletRequest request) {
        Exceptiondto exceptiondto = new Exceptiondto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptiondto);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exceptiondto> handleGenericException(Exception e , HttpServletRequest request) {
        Exceptiondto exceptiondto = new Exceptiondto(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptiondto);
    }
}
