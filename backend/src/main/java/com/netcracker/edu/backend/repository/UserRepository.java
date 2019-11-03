package com.netcracker.edu.backend.repository;

import com.netcracker.edu.backend.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User,Integer> {
    //@Query("select u from User u where u.email = :email")
    User findByEmailAndPassword(String email,String password);
}
