package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.security.jwt.JwtAuthenticationException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(JwtAuthenticationException.class)
    protected ResponseEntity<?> handleInvalidToken(JwtAuthenticationException ex, WebRequest request) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "JWT token is expired or invalid");
        return ResponseEntity.status(403).body(error);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<?> handleExpiredToken(ExpiredJwtException ex, WebRequest request) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "JWT token is expired or invalid");
        return ResponseEntity.status(403).body(error);
    }
}
