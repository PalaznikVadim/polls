package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> findAll(String search, int page, int size, String sort, String order);

    User save(User user);

    User findById(int id);

    User getByEmail(String email);
}
