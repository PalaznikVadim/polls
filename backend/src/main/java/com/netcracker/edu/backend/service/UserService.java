package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

import java.util.Optional;

public interface UserService {
    User findByEmailAndPassword(String email,String password);
    Iterable<User> findAll();
    Optional<User> findUserById(Integer id);
    User save(User user);
}
