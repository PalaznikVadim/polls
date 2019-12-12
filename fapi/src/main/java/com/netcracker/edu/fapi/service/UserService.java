package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> findAll(String search, Integer page, Integer size, String sort, String order);

    User save(User user);

    User findById(Integer id);

    User[] getAll();

    User getByEmail(String email);
}
