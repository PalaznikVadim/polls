package com.netcracker.edu.fapi.service;

import org.springframework.http.ResponseEntity;

public interface TokenService {
    ResponseEntity<?> authenticate(String email, String password);
}
