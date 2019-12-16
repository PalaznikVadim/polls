package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User findByEmailAndPassword(String email, String password);

    Page<User> findAll(String search, int page, int size, String sort, String order);

    Optional<User> findUserById(int id);

    User save(User user);

    User getByEmail(String email);
}
