package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> findByEmailAndPassword(String email,String password);
//    Page<User> findAll(Integer page,String field);
    ResponseEntity<?> findAll(Integer page, Integer size, String sort, String order);
    ResponseEntity<?> save(User user);
    User findById(Integer id);
    User[] getAll();
    User getByEmail(String email);
}
