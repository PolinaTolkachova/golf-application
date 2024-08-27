package com.golf.app.controller;


import com.golf.app.exception.CompetitionNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CompetitionExceptionHandlerController {

    protected static final String ERROR_PAGE = "error/error-page";

    @ExceptionHandler(CompetitionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCompetitionNotFoundException(CompetitionNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
