package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.entity.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface UserRepository extends CrudRepository<User,Integer> {
    User findByEmailAndPassword(String email,String password);
    List<User> findAllByRole(Role role);
}
