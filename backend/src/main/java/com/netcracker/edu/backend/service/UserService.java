package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

public interface UserService {
    User findByEmailAndPassword(String email,String password);
//    Page<User> findAll(Integer page, String sortedField, Pageable pageable);
    Page<User> findAll(Pageable pageable);
    Optional<User> findUserById(Integer id);
    User save(User user);
    User getByEmail(String email);
}
