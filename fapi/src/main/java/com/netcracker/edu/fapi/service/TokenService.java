package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;
import org.springframework.http.ResponseEntity;

public interface TokenService {
    ResponseEntity<?> authenticate(String email, String password);

    ResponseEntity<?> registration(User user);

    User loadByToken(String token);
}
