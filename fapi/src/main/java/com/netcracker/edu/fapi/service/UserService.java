package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;
import java.util.List;

public interface UserService {

    User findByEmailAndPassword(String email,String password);
    List<User> findAll();
    User save(User user);
    User findById(Integer id);
}
