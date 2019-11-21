package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User findByEmailAndPassword(String email,String password);
//    Page<User> findAll(Integer page,String field);
    Page<User> findAll(Integer page, Integer size, String sort, String order);
    User save(User user);
    User findById(Integer id);
    User[] getAll();
}
