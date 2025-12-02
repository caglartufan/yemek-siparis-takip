package com.caglartufan.yemek_siparis_takip.exception;

import com.caglartufan.yemek_siparis_takip.response.ResourceNotFoundResponse;
import com.caglartufan.yemek_siparis_takip.response.ValidationFailedResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<@NonNull ValidationFailedResponse> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ValidationFailedResponse res = new ValidationFailedResponse(errors);
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler
    public ResponseEntity<@NonNull ResourceNotFoundResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResourceNotFoundResponse res = new ResourceNotFoundResponse(ex.getMessage());

        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
}
